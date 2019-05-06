package com.sj.common.vo;

import java.util.List;

import com.sj.common.pojo.Movie;
import com.sj.common.pojo.Purchase;


public class FilmsInfo {
	//总页数
		private Integer totalPage;
		//当前页数
		private Integer currentPage;
		
		private List<MovieDetail> movieDetails;
		//查询分页结果
		
		public List<MovieDetail> getMovieDetails() {
			return movieDetails;
		}
		public void setMovieDetails(List<MovieDetail> movieDetails) {
			this.movieDetails = movieDetails;
		}
		public Integer getTotalPage() {
			return totalPage;
		}
		public void setTotalPage(Integer totalPage) {
			this.totalPage = totalPage;
		}
		public Integer getCurrentPage() {
			return currentPage;
		}
		public void setCurrentPage(Integer currentPage) {
			this.currentPage = currentPage;
		}
		
		
		

}
