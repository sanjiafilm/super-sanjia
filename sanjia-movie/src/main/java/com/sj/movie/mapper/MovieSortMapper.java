package com.sj.movie.mapper;

import java.util.List;

import com.sj.common.pojo.Movie;

public interface MovieSortMapper {
	List<Movie> sortData();
	List<Movie> sortScore();

}
