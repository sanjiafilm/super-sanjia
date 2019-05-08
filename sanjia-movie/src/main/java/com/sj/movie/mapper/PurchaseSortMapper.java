package com.sj.movie.mapper;


import java.util.List;
import java.util.Date;
import java.sql.Timestamp;

import org.apache.ibatis.annotations.Param;

import com.sj.common.pojo.Purchase;

public interface PurchaseSortMapper {

	List<Purchase> sortPlayTime(@Param("name")String name,
			@Param("todaytime") Date todaytime, 
			@Param("tomorrowtime") Date tomorrowtime);

//	List<Purchase> sortPlayTime(Date date);

/*	List<Purchase> sortPlayTime(@Param("name") String name,
			@Param("date") Date date,
			@Param("tomorrow") Date tomorrow);*/
	//	List<Purchase> sortPlayTime(@Param("name") String name);


}
