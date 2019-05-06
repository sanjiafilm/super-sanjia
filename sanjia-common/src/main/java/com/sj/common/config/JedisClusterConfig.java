package com.sj.common.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisClusterConfig {
	@Value("${spring.redis.pool.nodes:10.42.27.36:8004,10.42.27.36:8002,10.42.27.36:8002}")
	private String nodes;
	@Value("${spring.redis.pool.maxTotal:200}")
	private Integer maxTotal;
	@Value("${spring.redis.pool.maxIdle:8}")
	private Integer maxIdle;
	@Value("${spring.redis.pool.minIdle:2}")
	private Integer minIdle;
	
	public String getNodes() {
		return nodes;
	}

	public void setNodes(String nodes) {
		this.nodes = nodes;
	}

	public Integer getMaxTotal() {
		return maxTotal;
	}

	public void setMaxTotal(Integer maxTotal) {
		this.maxTotal = maxTotal;
	}

	public Integer getMaxIdle() {
		return maxIdle;
	}

	public void setMaxIdle(Integer maxIdle) {
		this.maxIdle = maxIdle;
	}

	public Integer getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(Integer minIdle) {
		this.minIdle = minIdle;
	}

	@Bean
	public JedisCluster initJedisCluster() {
		Set<HostAndPort> set=new HashSet<>();
		String[] node=nodes.split(",");
		for(String hostAndPost : node) {
			String host=hostAndPost.split(":")[0];
			int port=Integer.parseInt
					(hostAndPost.split(":")[1]);
			set.add(new HostAndPort(host,port));
		}
		JedisPoolConfig config=new JedisPoolConfig();
		config.setMaxTotal(maxTotal);
		config.setMaxIdle(maxIdle);
		config.setMinIdle(minIdle);
		return new JedisCluster(set,config);
	
		
	}
	
	
}
