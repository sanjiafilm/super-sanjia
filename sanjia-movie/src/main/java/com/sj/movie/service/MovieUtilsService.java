package com.sj.movie.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sj.movie.entity.JedisEntity;
import com.sj.movie.mapper.MovieUtilsMapper;

import redis.clients.jedis.JedisCluster;

@Service
public class MovieUtilsService {
	@Autowired
	private MovieUtilsMapper movieUtilsMapper;
	
	@Resource
	private JedisCluster initJedisCluster;
	public void newMovieType() {
		//添加数据
		List<JedisEntity> allMovieType = movieUtilsMapper.getAllMovieType();
		for (JedisEntity jedisEntity : allMovieType) {
			String[] split = jedisEntity.getCatagory().split("/");
			for (String str : split) {
				initJedisCluster.lpush(str,Integer.toString(jedisEntity.getId()));
			}
		}
	}
	
	//获取存在的id
	public List<String> getJedisTypeId(String Type) {
		List<String> lrange = initJedisCluster.lrange(Type, 0, -1);
		return lrange;
	}

}
