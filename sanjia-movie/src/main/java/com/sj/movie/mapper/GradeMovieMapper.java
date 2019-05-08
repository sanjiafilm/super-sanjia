package com.sj.movie.mapper;



import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sj.common.pojo.Cinema;
import com.sj.common.pojo.Movie;
import com.sj.common.pojo.Purchase;

public interface GradeMovieMapper {

	String getGradeName(@Param(value="one")Integer one,@Param(value="two")Integer twp);
	Movie getGradeData(@Param(value="name")String name,@Param(value="oneday")Timestamp dateOne,@Param(value="towday")Timestamp dataTwo);
	Integer getGradeMovieCountMapper();
	Cinema getCinema(String cinameName);
	
	List<Purchase> selectPurchase(@Param(value="l_lat")Double l_lat , @Param(value="l_lng")Double l_lng , @Param(value="movieName")String movieName);//查询电影票的购票信息
	
	List<Movie> selectAllMovie(@Param(value="l_lat")Double l_lat , @Param(value="l_lng")Double l_lng);


}
