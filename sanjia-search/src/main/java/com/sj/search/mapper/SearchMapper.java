package com.sj.search.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sj.common.pojo.Movie;
import com.sj.common.pojo.Purchase;

public interface SearchMapper {

	Movie getMovieInfo(String name);

	List<Purchase> getPurchaseInfo(@Param("movieName") String movieName,
			@Param("todaytime") Date todaytime, 
			@Param("tomorrowtime") Date tomorrowtime);

	Integer getall();

}
