package com.sj.movie.mapper;




import java.sql.Timestamp;

import org.apache.ibatis.annotations.Param;

import com.sj.common.pojo.Movie;

public interface MovieInfoMapper {

	Movie getMovieInfoByName(@Param(value="movieName")String name,@Param(value="oneday")Timestamp dayOneSql,@Param(value="towday")Timestamp dayTwoSql,@Param(value="page")Integer page);

	Integer getInfoCount(@Param(value="name")String name,@Param(value="oneday")Timestamp dayOneSql,@Param(value="towday")Timestamp dayTwoSql);

}
