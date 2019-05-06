package com.sj.movie.entity;

import java.util.List;

public class MovieVo {
private List<Integer> jedisType;
private Integer page;
public List<Integer> getJedisType() {
	return jedisType;
}
public void setJedisType(List<Integer> jedisType) {
	this.jedisType = jedisType;
}
public Integer getPage() {
	return page;
}
public void setPage(Integer page) {
	this.page = page;
}



}
