package com.busap.cctv.baidudata.docking.task;

import com.busap.cctv.baidudata.docking.redis.JedisResultSet;
import com.busap.cctv.baidudata.docking.redis.TargetJedisResultSet;
import com.busap.cctv.baidudata.docking.repository.BusTerminalRepository;

/**
 * GPS消息任务处理类
 * <p><blockquote>
 * 主要包括： 
 * 	GPS任务处理
 * </blockquote></p>
 * @author OAK <a href="mailto:shulong.li@busonline.com">shulong.li@busonline.com</a>
 * @see ExecutorTask
 * @since CCTV BaiDu 1.0
 * @version $Revision: 1.0 $ $Date: 2016/07/27 14:28:00 $ $LastModified 2016/07/27 14:28:00 $
 */
public class GPSExecutorTask extends ExecutorTask {
	
	public GPSExecutorTask(BusTerminalRepository repository, JedisResultSet resultSet,
			TargetJedisResultSet targetResultSet, String [] fields, String redisKey, Integer index) {
		super(repository, resultSet, targetResultSet, fields, redisKey, index);
	}
	
}
