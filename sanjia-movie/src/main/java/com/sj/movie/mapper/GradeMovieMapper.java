package com.sj.movie.mapper;



import java.sql.Timestamp;

import org.apache.ibatis.annotations.Param;

import com.sj.common.pojo.Movie;

public interface GradeMovieMapper {

	String getGradeName(@Param(value="one")Integer one,@Param(value="two")Integer twp);
	Movie getGradeData(@Param(value="name")String name,@Param(value="oneday")Timestamp dateOne,@Param(value="towday")Timestamp dataTwo);
	Integer getGradeMovieCountMapper();


}
