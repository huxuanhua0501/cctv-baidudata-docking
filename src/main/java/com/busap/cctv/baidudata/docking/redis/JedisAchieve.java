package com.busap.cctv.baidudata.docking.redis;

import redis.clients.jedis.Jedis;

public interface JedisAchieve {
	
	Jedis getJedis();
	
	void returnResource(Jedis jedis);
}
