package com.busap.cctv.baidudata.docking.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class TargetJedisResultSet extends BaseJedis {

	@Autowired
	private JedisPool targetJedisPool;

	/*
	 * 通过redis连接池获取Jedis对象
	 */
	public Jedis getJedis() {
		return targetJedisPool.getResource();
	}

	/*
	 * 释放占用的jedis资源
	 */
	public void returnResource(Jedis jedis) {
		targetJedisPool.returnResource(jedis);
	}

}
