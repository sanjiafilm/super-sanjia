package com.sj.movie.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sj.common.pojo.Movie;
import com.sj.movie.entity.MovieVo;

public interface TimeAndTypeMapper {

	List<String> getMovieName(MovieVo movieType);

	Movie getMovieData(@Param(value="name")String name,@Param(value="oneday")Timestamp dateOne,@Param(value="towday")Timestamp dataTwo);

	int getCount();

}
