package com.busap.cctv.baidudata.docking.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.busap.cctv.baidudata.docking.domain.BusTerminal;
import com.busap.cctv.baidudata.docking.redis.JedisResultSet;
import com.busap.cctv.baidudata.docking.redis.TargetJedisResultSet;
import com.busap.cctv.baidudata.docking.repository.BusTerminalRepository;
import com.busap.cctv.baidudata.docking.utility.Pinyin;
import com.busap.cctv.baidudata.docking.utility.Pinyin.Type;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;

import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * GPS消息任务处理基类
 * <p><blockquote>
 * 主要包括： 
 * 	GPS任务处理
 * 	城市汉子转换拼音输入法
 * 	取REDIS队列LIST里的GPS消息
 * 	处理得到的GPS消息并存入HashSet中
 * </blockquote></p>
 * @author OAK <a href="mailto:shulong.li@busonline.com">shulong.li@busonline.com</a>
 * @since CCTV BaiDu 1.0
 * @version $Revision: 1.0 $ $Date: 2016/07/27 14:28:00 $ $LastModified 2016/07/27 14:28:00 $
 */
public abstract class ExecutorTask extends LocationUtils implements Runnable {
	
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	/**
	 * 线程运行状态
	 */
	private volatile boolean isRunning = true;
	
	/**
	 * 车载终端Repository
	 */
	private BusTerminalRepository repository;
	
	/**
	 * 读取的REDIS库
	 */
	private JedisResultSet resultSet;
	
	/**
	 * 存入的目标REDIS库
	 */
	private TargetJedisResultSet targetResultSet;
	
	/**
	 * REDIS DB库索引
	 */
	private Integer index;
	
	/**
	 * 终端设备传递过来的按顺序排列的字符串分割后的命名属性数组
	 */
	private String [] fields;
	
	/**
	 * REDIS队列里的KEY值
	 */
	private String redisKey;
	
	/**
	 * 汉语拼音汉字解析工具类对象
	 */
	private static final Pinyin pinyin = Pinyin.tools();
	
	/**
	 * 终端设备传递过来的字符串分隔符(♂♀)
	 */
	private static final String CH = " ";
	
	/**
	 * 车载终端（已激活）状态
	 */
	private static final Short STATUS = new Short("2");
	
	/**
	 * 拼音小写输入法
	 */
	private static final Type LOWERCASE = Type.LOWERCASE;
	
	/**
	 * 年月日格式
	 */
	private static final SimpleDateFormat YMD = new SimpleDateFormat("yyyy-MM-dd");
	
	/**
	 * 24小时至分秒格式
	 */
	private static final SimpleDateFormat HMS = new SimpleDateFormat("HH:mm:ss");
	
	private static double EARTH_RADIUS = 6378.137;  
	  
    private static double rad(double d) {  
        return d * Math.PI / 180.0;  
    }  
  

	public ExecutorTask(BusTerminalRepository repository, JedisResultSet resultSet, TargetJedisResultSet targetResultSet, 
			String [] fields, String redisKey, Integer index) {
		this.repository = repository;
		this.resultSet = resultSet;
		this.targetResultSet = targetResultSet;
		this.fields = fields;
		this.redisKey = redisKey;
		this.index = index;
	}
	
	public void run() {
		String value = null;
		while (isRunning) {
			//if(resultSet.get(0, redisKey)!=null&& ! resultSet.get(0, redisKey).equals("")){
				try{
					value = resultSet.rpop(index, redisKey);//根据KEY读取从头部的REDIS库中队列的值
					 if(value != null){
					    //System.out.println(value);
						 LOG.info("gps参数"+value);
						 String[] strs = value.split(" ");
						if(strs.length==6){
						BusTerminal bus = repository.findByAppKeyAndStatus(strs[0], STATUS);
						if(bus != null){
							ConcurrentHashMap<String,String> cmap = new ConcurrentHashMap<String,String>();
							cmap.put("dataType", "GPS");
							cmap.put("dplBus", strs[0]);
							cmap.put("dplLine", bus.getBusLine().getName());
							Long timestamp = Long.parseLong(strs[5]);
							Date timeDate = new Date(timestamp);
							cmap.put("date", new SimpleDateFormat("yyyy-MM-dd").format(timeDate));
							cmap.put("time", new SimpleDateFormat("HH:mm:ss").format(timeDate));
							cmap.put("longitude", strs[3]);
							cmap.put("latitude", strs[2]);
							cmap.put("velocity", strs[4]);
							cmap.put("direction", strs[1]);
							/**
							 * 判断距离，调取已存的经纬度（终点）(只比对出站的始发站的经纬度，不比较回来的终点站的经纬度)，
							 * 进行判断，接近后打上下行（打点）
							 */
							List<String> liststop = targetResultSet.hget2(2, "stoptask", bus.getBusLine().getId()+"_0");//上行
							List<String> liststop1 = targetResultSet.hget2(2, "stoptask", bus.getBusLine().getId()+"_1");//下行
//							System.out.println(liststop.get(0));
							JSONObject jsonObj = (JSONObject) JSONObject.parse(liststop.get(0));
							JSONObject jsonObj1 = (JSONObject) JSONObject.parse(liststop1.get(0));
//							bus.et
							if(null !=jsonObj && null !=jsonObj ){
								double distance =  this.getDistance(Double.parseDouble((String) jsonObj.get("lat")), Double.parseDouble((String) jsonObj.get("lon")), Double.parseDouble(strs[2]), Double.parseDouble(strs[3]));
								double distance1 =  this.getDistance(Double.parseDouble((String) jsonObj1.get("lat")), Double.parseDouble((String) jsonObj1.get("lon")), Double.parseDouble(strs[2]), Double.parseDouble(strs[3]));
								if(distance<=800&&jsonObj.get("linedir").equals("0")){
									targetResultSet.set(2, strs[0], "0");
								}else if(distance1<=800&&jsonObj1.get("linedir").equals("1")){
									targetResultSet.set(2, strs[0], "1");
								}
							}
							String lineDirection=  targetResultSet.get(2, strs[0]);
							if(lineDirection==null){
								cmap.put("lineDirection", "");
							}else{
								cmap.put("lineDirection", targetResultSet.get(2, strs[0]));
							}
							String  lineId = bus.getBusLine().getId().toString(), 
									areaName = bus.getBusLine().getArea().getName(),
									areaCode = pinyin.toPinYin(areaName, "", LOWERCASE),
									key = areaCode + "_" + lineId;
							targetResultSet.hset(index, key, strs[0], JSON.toJSONString(cmap));
						}
						
					}
						}
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					LOG.error("城市名称转换拼音发生错误, 详细信息:" + e.getMessage());
				} catch (NullPointerException ex) {
					LOG.error("GPS发布消息处理失败, [终端设备传输的字符串: " + value + "], 空指针异常详细信息:" + ex.getMessage());
				} catch (IllegalArgumentException ex) {
					LOG.error("GPS发布消息处理失败, [终端设备传输的字符串: " + value + "], 参数不匹配详细信息:" + ex.getMessage());
				} catch (RuntimeException ex) {
					LOG.error("GPS发布消息处理失败, [终端设备传输的字符串: " + value + "], 运行时详细信息:" + ex.getMessage());
				} catch (Exception ex) {
					LOG.error("GPS发布消息处理失败, [终端设备传输的字符串: " + value + "], 详细信息:" + ex.getMessage());
				}
			//}
			
		}	
	}
	
	
	

	
	
	

    /**
     * 启动任务线程
     */
    public void start() {
    	isRunning = true;
    }
    
    /**
     * 关闭任务线程
     */
    public void shutdown() {
    	isRunning = false;
    }
//	public static void main(String[] args) {
//		Long timestamp = Long.parseLong("1469501881000");
//		Date timeDate = new Date(timestamp);
//		String str = new SimpleDateFormat("HH:mm:ss").format(timeDate);
//		String str1 = new SimpleDateFormat("yyyy-MM-dd").format(timeDate);
//		System.out.println(str);
//		System.out.println(str1);
////		String time = "1469501881000";
//	}
	
//	ConcurrentHashMap<String,String> cmap = new ConcurrentHashMap<String,String>();
// 
//	cmap.put("dataType", "GPS");
// 
//	cmap.put("velocity", "12313");
//	cmap.put("direction", "342342");
//	cmap.put("lineDirection", "");
//	System.out.println(cmap.toString());
//}
}
