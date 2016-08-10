package com.busap.cctv.baidudata.docking.service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.busap.cctv.baidudata.docking.redis.JedisResultSet;
import com.busap.cctv.baidudata.docking.redis.TargetJedisResultSet;
import com.busap.cctv.baidudata.docking.repository.BusTerminalRepository;

/**
 * 线程池处理业务基类
 * <p><blockquote>
 * 主要包括： 
 * 	初始化线程池相关配置参数
 * 	实例化线程池
 * 	调用线程池添加TASk
 * 	关闭销毁线程池
 * </blockquote></p>
 * @author OAK <a href="mailto:shulong.li@busonline.com">shulong.li@busonline.com</a>
 * @since CCTV BaiDu 1.0
 * @version $Revision: 1.0 $ $Date: 2016/07/27 14:28:00 $ $LastModified 2016/07/27 14:28:00 $
 */
public abstract class AbstractService {
	
	@Autowired
	protected BusTerminalRepository busTerminalRepository;
	
	@Autowired
	protected JedisResultSet resultSet;
	
	@Autowired
	protected TargetJedisResultSet targetResultSet;
	
	private LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<Runnable>();
	
	@Value(value="${thead.pool.corePoolSize}")
	private Integer corePoolSize;
	
	@Value(value="${thead.pool.maximumPoolSize}")
	private Integer maximumPoolSize;
	
	@Value(value="${thead.pool.keepAliveTime}")
	private Long keepAliveTime;
	
	@Value(value="${thead.pool.unit}")
	private String unitName;

	@Value(value="${thead.pool.taskCount}")
	protected Integer taskCount;
	
	@Value(value = "${type.of.gps}")
	protected String GPS;
	
	protected final String [] fields = new String [] { "dataType", "dplBus", "direction", "latitude", "longitude", "velocity", "time", "date", "dplLine", "lineDirection" };
	
	private ExecutorService services;
	
	public void newFixedThreadPool() {
		services = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
				keepAliveTime, TimeUnit.valueOf(unitName), workQueue);
	}
	
	public void execute(Runnable task){
		services.execute(task);
	}
	
	public void shutdown(){
		services.shutdown();
	}
	
}
