package com.sj.search.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sj.common.pojo.Movie;
import com.sj.common.pojo.ObjectUtil;
import com.sj.common.pojo.Purchase;
import com.sj.common.vo.MovieDetail;
import com.sj.search.mapper.SearchMapper;
import com.sj.search.service.SearchService;

@RestController
public class SearchController {
	@Autowired
	private SearchService searchService;
	@Autowired
	private SearchMapper searchMapper;
	
	@RequestMapping("associate/{searchkey}")
	public String getFilmName(@PathVariable String searchkey){
		List<String> filmNameL = new ArrayList<String>();
		filmNameL =searchService.getFilmName(searchkey);
//		model.addAttribute("filmNames", filmNameL);
		try {
			String filmNames = ObjectUtil.mapper.writeValueAsString(filmNameL);
			
				return filmNames;
						
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	
	/*@RequestMapping("films")
	public MovieDetail getFilmDetail(String movieName) {
		return searchService.getFilmDetail(movieName);
	}*/
	
	@RequestMapping("movies")
	public Movie getMovieInfo(String movieName ,Integer page) {
		return searchService.getFilmDetail(movieName,page);
	}
	
	@RequestMapping("purchase")
	public List<Purchase> getpurchInfo(String movieName) {
		return searchService.getpurchInfo(movieName);
	}
	
	@RequestMapping("test")
	public Integer index() {
		String name = "何以为家";
		Integer count = searchMapper.getall();
		Movie movie = searchMapper.getMovieInfo(name);
		List<Purchase> purchaseL = searchMapper.getPurchaseInfo(name);
		return count;
	}
}
