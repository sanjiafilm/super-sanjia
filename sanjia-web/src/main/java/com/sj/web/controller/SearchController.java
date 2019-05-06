package com.sj.web.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sj.common.pojo.Movie;
import com.sj.common.pojo.ObjectUtil;
import com.sj.common.vo.FilmsDetail;
import com.sj.common.vo.FilmsInfo;
import com.sj.common.vo.MovieDetail;
import com.sj.web.service.SearchService;



@Controller
@RequestMapping("search")
public class SearchController {
	@Autowired
	private SearchService searchService;
	
	@RequestMapping(value="associate/{searchkey}",method=RequestMethod.GET)
	@ResponseBody
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
	
	@RequestMapping(value="films",method=RequestMethod.GET)	
	public String getFilmDetail(String text,Model model) {
		FilmsInfo filmsInfo = new FilmsInfo();
		try {
			/*JsonNode josndata = ObjectUtil.mapper.readTree(json);
			List<String> movieNameL = new ArrayList<>();
			if(josndata.isArray()&&josndata.size()>0){
				//data数据合法,第一个参数是jsonNode获取的数据
				//解析器parser
				//第二个参数,构造一个返回的类型工程,List<Cart>
				movieNameL=ObjectUtil.mapper.readValue(josndata.traverse()
						, ObjectUtil.mapper
						.getTypeFactory().constructCollectionType(List.class, String.class));
			}*/
			System.out.println(text);
			List<String> filmNameL = new ArrayList<String>();
			filmNameL =searchService.getFilmName(text);
			Integer count = filmNameL.size();
			List<MovieDetail> movieds =searchService.getFilmDetail(filmNameL);
			filmsInfo.setTotalPage(1);
			filmsInfo.setCurrentPage(1);
			filmsInfo.setMovieDetails(movieds);
			model.addAttribute("page", filmsInfo);
//			return filmsInfo;
			return "movies";
		}catch(Exception e) {
			System.out.println("error ");
			e.printStackTrace();
			return "error";
//			return null;
		}
		
	}
	
	@RequestMapping(value="page",method=RequestMethod.GET)
	public FilmsDetail getFilmPage(String text,Model model) {
		FilmsDetail filmsDetail = new FilmsDetail();
		List<Movie> movies = new ArrayList<Movie>();
		System.out.println(text);
		List<String> filmNameL = new ArrayList<String>();
		filmNameL =searchService.getFilmName(text);
		Integer count = filmNameL.size();
		filmsDetail.setTotalPage(1);
		filmsDetail.setCurrentPage(1);			
		movies=searchService.getMovieInfo(filmNameL);
		filmsDetail.setMovies(movies);
		model.addAttribute("page", filmsDetail);
		return filmsDetail;
		
	}

}
