package com.busap.cctv.baidudata.docking.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;
import com.busap.cctv.baidudata.docking.task.GPSExecutorTask;

/**
 * GPS消息多通道处理业务类
 * <p><blockquote>
 * 主要包括： 
 * 	自定义线程池
 * 	接收REDIS队列LIST里的GPS消息
 * 	查询车载终端、线路、城市相关的数据
 * 	处理GPS消息并加入到自定义的JSONObject中
 * </blockquote></p>
 * @author OAK <a href="mailto:shulong.li@busonline.com">shulong.li@busonline.com</a>
 * @see AbstractService
 * @since CCTV BaiDu 1.0
 * @version $Revision: 1.0 $ $Date: 2016/07/27 14:28:00 $ $LastModified 2016/07/27 14:28:00 $
 */
@Service
public class GPSService extends AbstractService {
	
	private final Integer dbIndex = new Integer(0);
	
	@PostConstruct
	private void init() {
		newFixedThreadPool();
		int i = 0;
		for(; i < taskCount; i++){
			this.execute(new GPSExecutorTask(busTerminalRepository, resultSet, targetResultSet, fields, GPS, dbIndex));
		}
	}
	
}
