package com.busap.cctv.baidudata.docking.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.busap.cctv.baidudata.docking.domain.BusTerminal;
import com.busap.cctv.baidudata.docking.redis.JedisResultSet;
import com.busap.cctv.baidudata.docking.redis.TargetJedisResultSet;
import com.busap.cctv.baidudata.docking.repository.BusTerminalRepository;
import com.busap.cctv.baidudata.docking.utility.Pinyin;

import static com.busap.cctv.baidudata.docking.utility.Pinyin.Type;

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
public abstract class ExecutorTask implements Runnable {
	
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
			try{
				value = resultSet.rpop(index, redisKey);//根据KEY读取从头部的REDIS库中队列的值
				if(value != null){
					
					String[] strs = value.split(",");
					ConcurrentHashMap<String,String> cmap = new ConcurrentHashMap<String,String>();
					BusTerminal bus = repository.findByAppKeyAndStatus(strs[0], STATUS);
					cmap.put("dataType", "GPS");
					cmap.put("dplBus", strs[0]);
					if(bus == null){
						cmap.put("dplLine", "");
						}
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
					 double distance =  getDistance(1.222,1.222,1.222,1.222);
					
					
					cmap.put("lineDirection", "");
					String lineId = bus.getBusLine().getId().toString(), 
							areaName = bus.getBusLine().getArea().getName(),
							areaCode = pinyin.toPinYin(areaName, "", LOWERCASE),
							key = areaCode + "_" + lineId;
					targetResultSet.hset(index, key, strs[0], cmap.toString());
					
					
					
//					String dataTransforStrings = value;
//					int beginIndex = -1, i = 0, maxSize = fields.length;
//					String [] dataTransforObjects = new String[maxSize];
//					dataTransforObjects[i++] = redisKey;
//					while((beginIndex = dataTransforStrings.indexOf(CH)) != -1){
//						dataTransforObjects[i++] = dataTransforStrings.substring(0, beginIndex);
//						dataTransforStrings = dataTransforStrings.substring(beginIndex + CH.length());
//					}
//					if(dataTransforStrings.length() > 0){
//						Long timestamp = Long.parseLong(dataTransforStrings);
//						Date timeDate = new Date(timestamp);
//						dataTransforObjects[i++] = HMS.format(timestamp);
//						dataTransforObjects[i++] = YMD.format(timeDate);
//					}
//					String appKey = dataTransforObjects[1];
//					BusTerminal busTerminal = repository.findByAppKeyAndStatus(appKey, STATUS);
//					if(busTerminal != null){
//						dataTransforObjects[i++] = busTerminal.getBusLine().getName();
//						dataTransforObjects[i] = "";
//						StringBuilder gson = new StringBuilder();
//						gson.append("{");
//						for(i = 0; i < maxSize; i++){
//							gson.append(String.format("\"%s\" : \"%s\"", fields[i], dataTransforObjects[i]));
//							if(maxSize - 1 != i){
//								gson.append(",");
//							}
//						}
//						gson.append("}");
//						String lineId = busTerminal.getBusLine().getId().toString(), 
//								areaName = busTerminal.getBusLine().getArea().getName(),
//								areaCode = pinyin.toPinYin(areaName, "", LOWERCASE),
//								key = areaCode + "_" + lineId;
//						targetResultSet.hset(index, key, appKey, gson.toString());
				//	}
				}
			} catch (BadHanyuPinyinOutputFormatCombination e) {
				LOG.error("城市名称转换拼音发生错误, 详细信息:" + e.getMessage());
			} catch (NullPointerException ex) {
				LOG.error("GPS发布消息处理失败, [终端设备传输的字符串: " + value + "], 详细信息:" + ex.getMessage());
			} catch (IllegalArgumentException ex) {
				LOG.error("GPS发布消息处理失败, [终端设备传输的字符串: " + value + "], 详细信息:" + ex.getMessage());
			} catch (RuntimeException ex) {
				LOG.error("GPS发布消息处理失败, [终端设备传输的字符串: " + value + "], 详细信息:" + ex.getMessage());
			} catch (Exception ex) {
				LOG.error("GPS发布消息处理失败, [终端设备传输的字符串: " + value + "], 详细信息:" + ex.getMessage());
			}
		}	
	}
	
	/** 
     * 通过经纬度获取距离(单位：米) 
     * @param lat1 
     * @param lng1 
     * @param lat2 
     * @param lng2 
     * @return 
     */  
    public  double getDistance(double lat1, double lng1, double lat2,  
                                     double lng2) {  
        double radLat1 = rad(lat1);  
        double radLat2 = rad(lat2);  
        double a = radLat1 - radLat2;  
        double b = rad(lng1) - rad(lng2);  
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)  
                + Math.cos(radLat1) * Math.cos(radLat2)  
                * Math.pow(Math.sin(b / 2), 2)));  
        s = s * EARTH_RADIUS;  
        s = Math.round(s * 10000d) / 10000d;  
        s = s*1000;  
        return s;  
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
