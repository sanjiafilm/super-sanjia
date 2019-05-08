package com.sj.web.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.pagehelper.Page;
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
		// String url = UrlAddr.searchqueryUrl +
		// UrlAddr.searchquerykey01+searchkey;
		String url = "http://search/associate/" + searchkey;
		try {
			String jsondatas = client.doGet(url);
			JsonNode data = ObjectUtil.mapper.readTree(jsondatas);
			if (data.isArray() && data.size() > 0) {
				filmsL = ObjectUtil.mapper.readValue(data.traverse(),
						ObjectUtil.mapper.getTypeFactory().constructCollectionType(List.class, String.class));
			}
			return filmsL;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * public List<MovieDetail> getFilmDetail(List<String> movieNameL) {
	 * List<MovieDetail> movieDetails = new ArrayList<MovieDetail>(); String url
	 * = UrlAddr.searchqueryUrl + UrlAddr.searchquerykey02; Map<String,Object>
	 * param=new HashMap<String,Object>(); try { for (String movieName :
	 * movieNameL) { param.put("movieName", movieName);
	 * 
	 * String data = client.doGet(url,param);
	 * if(!StringUtil.isNullOrEmpty(data)) { MovieDetail movie =
	 * ObjectUtil.mapper.readValue(data, MovieDetail.class);
	 * movieDetails.add(movie); } } return movieDetails;
	 * 
	 * }catch(Exception e) { e.printStackTrace(); return null; } }
	 * 
	 * 
	 * 
	 * public List<Movie> getMovieInfo(List<String> movieNameL){ List<Movie>
	 * movies = new ArrayList<Movie>(); String url = UrlAddr.searchqueryUrl +
	 * UrlAddr.searchquerykey03; Map<String,Object> param=new
	 * HashMap<String,Object>(); try { for (String movieName : movieNameL) {
	 * param.put("movieName", movieName); String data = client.doGet(url,param);
	 * if(!StringUtil.isNullOrEmpty(data)) { Movie movie =
	 * ObjectUtil.mapper.readValue(data, Movie.class); movies.add(movie); } }
	 * return movies;
	 * 
	 * }catch(Exception e) { e.printStackTrace(); return null; } }
	 * 
	 * public List<Purchase> getpurchInfo(List<String> movieNameL){
	 * List<Purchase> purchases = new ArrayList<Purchase>(); String url =
	 * UrlAddr.searchqueryUrl + UrlAddr.searchquerykey04; Map<String,Object>
	 * param=new HashMap<String,Object>(); try { for (String movieName :
	 * movieNameL) { param.put("movieName", movieName); String data =
	 * client.doGet(url,param); if(!StringUtil.isNullOrEmpty(data)) { Purchase
	 * purchase = ObjectUtil.mapper.readValue(data, Purchase.class);
	 * purchases.add(purchase); } } return purchases;
	 * 
	 * }catch(Exception e) { e.printStackTrace(); return null; } }
	 */

	public String getMovieDetail(List<String> filmNameL,
			@RequestParam(required = false, defaultValue = "1") Integer page) {
		Map<String, Object> param = new HashMap<String, Object>();
		List<Movie> movies = new ArrayList<>();
		HttpClient httpClient = HttpClients.createDefault();
		String url = "http://search/movies?movieName=";
		try {
			for (String movieName : filmNameL) {
				HttpGet httpGet = new HttpGet(url + movieName + "&page=" + 1);
				HttpResponse response = httpClient.execute(httpGet);
				HttpEntity entity = response.getEntity();
				String res = ToStr.tostr(entity.getContent());
				if (StringUtils.isNotBlank(res)) {
					Movie m = ObjectUtil.mapper.readValue(res, Movie.class);
					movies.add(m);
				}
			}
			int count = filmNameL.size();
			int totalPage = count > 3 ? (count % 3 == 0 ? (count / 3) : count / 3 + 1) : 1;
			Page<Movie> p = new Page<>(totalPage, 3);
			p.setTotal(count);
			if(page<totalPage)
			{
				for(int i=(page-1)*3;i<3;i++){
					p.add(movies.get(i));
				}			
			}
			else{
				if(count%3==1){
					p.add(movies.get(count-1));
				}
				else{
					p.add(movies.get(count-2));
					p.add(movies.get(count-1));
				}
			}
			PageInfo<Movie> pageL = p.toPageInfo();

			String res = ObjectUtil.mapper.writeValueAsString(pageL);
			System.out.println(res);

			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
