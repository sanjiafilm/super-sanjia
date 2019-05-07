package com.sj.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.lucene.analysis.payloads.PayloadHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sj.common.config.HttpClientService;
import com.sj.common.config.UrlAddr;
import com.sj.common.pojo.Movie;
import com.sj.common.pojo.ObjectUtil;
import com.sj.common.pojo.Purchase;
import com.sj.common.utils.ToStr;
import com.sj.common.vo.MovieDetail;

import io.netty.util.internal.StringUtil;

@Service
public class SearchService {
	@Autowired
	private HttpClientService client;

	public List<String> getFilmName(String searchkey) {
		List<String> filmsL = new ArrayList<String>();
//		String url = UrlAddr.searchqueryUrl + UrlAddr.searchquerykey01+searchkey;
		String url ="http://search/associate/"+searchkey;
		try {
			String jsondatas = client.doGet(url);
			JsonNode data = ObjectUtil.mapper.readTree(jsondatas);
			if(data.isArray() && data.size()>0){
				filmsL = ObjectUtil.mapper.readValue(data.traverse(),
						ObjectUtil.mapper.getTypeFactory().constructCollectionType(List.class, String.class));
			}
			return filmsL;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*public List<MovieDetail> getFilmDetail(List<String> movieNameL) {
		List<MovieDetail> movieDetails = new ArrayList<MovieDetail>();
		String url = UrlAddr.searchqueryUrl + UrlAddr.searchquerykey02;
		Map<String,Object> param=new HashMap<String,Object>();
		try {
			for (String movieName : movieNameL) {
				param.put("movieName", movieName);
				
				String data = client.doGet(url,param);
				if(!StringUtil.isNullOrEmpty(data)) {					
					MovieDetail movie = ObjectUtil.mapper.readValue(data, MovieDetail.class);
					movieDetails.add(movie);
				}
			}
			return movieDetails;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	public List<Movie> getMovieInfo(List<String> movieNameL){
		List<Movie> movies = new ArrayList<Movie>();
		String url = UrlAddr.searchqueryUrl + UrlAddr.searchquerykey03;
		Map<String,Object> param=new HashMap<String,Object>();
		try {
			for (String movieName : movieNameL) {
				param.put("movieName", movieName);
				String data = client.doGet(url,param);
				if(!StringUtil.isNullOrEmpty(data)) {					
					Movie movie = ObjectUtil.mapper.readValue(data, Movie.class);
					movies.add(movie);
				}
			}
			return movies;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Purchase> getpurchInfo(List<String> movieNameL){
		List<Purchase> purchases = new ArrayList<Purchase>();
		String url = UrlAddr.searchqueryUrl + UrlAddr.searchquerykey04;
		Map<String,Object> param=new HashMap<String,Object>();
		try {
			for (String movieName : movieNameL) {
				param.put("movieName", movieName);
				String data = client.doGet(url,param);
				if(!StringUtil.isNullOrEmpty(data)) {					
					Purchase purchase = ObjectUtil.mapper.readValue(data, Purchase.class);
					purchases.add(purchase);
				}
			}
			return purchases;
			
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}*/

	public String getMovieDetail(List<String> filmNameL,Integer page) {
		 Map<String,Object> param=new HashMap<String,Object>();
		 List<Movie> movies = new ArrayList<>();
		 HttpClient httpClient = HttpClients.createDefault();
		 
		 String url="http://search/movies?movieName="; 
	        try{
	        	for (String movieName : filmNameL) {
	        		HttpGet httpGet = new HttpGet(url + movieName+"&page="+page);
	        		HttpResponse response = httpClient.execute(httpGet);
	        		HttpEntity entity = response.getEntity(); 
	        		String res = ToStr.tostr(entity.getContent());
	        		Movie m = ObjectUtil.mapper.readValue(res, Movie.class);
	        		System.out.println(res);
//	        		Movie m = (Movie) JSONObject.parse(res);
	        		movies.add(m);
	        	}	        	
	        	String res = ObjectUtil.mapper.writeValueAsString(new PageInfo<>(movies));
	    		return res;
	        }catch(Exception e){
	        	e.printStackTrace();
	        	return null;
	        }
		
	}
	
	



}
