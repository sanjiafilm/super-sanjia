package com.sj.movie.controller;

import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;
import com.sj.common.pojo.Purchase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.sj.common.pojo.Movie;
import com.sj.movie.service.GradeMovieService;
import com.sj.movie.service.MoiveByTimeAndType;
import com.sj.movie.service.MovieInfoService;
import com.sj.movie.service.MovieUtilsService;
import com.sj.movie.utils.JdiesMovieType;

import io.netty.util.internal.ObjectUtil;



@Controller
public class GradeMovieController {
	@Resource
	private GradeMovieService gradeService;
	@RequestMapping(value="/",method=RequestMethod.GET)
	@ResponseBody
	public List<Movie> gradeMoive(String page){
		//page当前页数
		int pageInt=Integer.parseInt(page);
		List<Movie> data= gradeService.getGradeMovieData(pageInt);
		int count=gradeService.getGradeMovieCount();
		System.out.println(count);
		return data ;
	}

	/**
	 * Description: 分页查询全部电影数据
	 *
	 * @Date: 2019/5/6 20:31
	 * @param: [page]
	 * @author: ls
	 */
	@RequestMapping("/all")
	@ResponseBody
	public PageInfo<Movie> getIndex(@RequestParam(required = false, defaultValue = "1") Integer page){
		return gradeService.getAll(page);
	}

	/**
	 * Description: 根据电影名称查询电影详情
	 *
	 * @Date: 2019/5/7 8:46
	 * @param: [name]
	 * @author: ls
	 */
	@RequestMapping("/movie_info")
	@ResponseBody
	public Movie movieInfo(String name){
		return gradeService.getMovieInfo(name);
	}
	/**
	 * Description: 分页查询购票信息
	 *
	 * @Date: 2019/5/6 22:27
	 * @param: []
	 * @author: ls
	 */
	@RequestMapping("/buy")
	@ResponseBody
	public PageInfo<Purchase> findPurchase(String name, Integer page){
		return gradeService.findPurchaseInfo(name, page);
	}
	
	
	//显示全部数据
	@Resource
	private MovieInfoService movieInfo;
		
	@RequestMapping(value="/movie/movieData",method=RequestMethod.GET)
	@ResponseBody
	public Movie getMovieDataByName(String movieName,String page) {
		
		Movie movie=movieInfo.getMovieInfo(movieName,Integer.parseInt(page));
		Integer count=movieInfo.getMovieInfoCount(movieName);
		System.out.println(count);
		return movie;
	}
	
	
	
	/*@Resource
	private JdiesMovieType jdiesMovieType;*/
	@Resource
	private MovieUtilsService movieUtilsService;
	@Resource
	private MoiveByTimeAndType moiveByTimeAndType;
	//通过类型查找数据
	@RequestMapping(value="/movie/movieType",method=RequestMethod.GET)
	@ResponseBody
	public List<Movie> getMovieDataByType(String movieTime, String movieType,String page) {
		//获取到该类型的id号
		List<String> jedisTypeId = movieUtilsService.getJedisTypeId(movieType);
		//System.out.println(jedisTypeId.size());
		//获取有关的数据
		List<Movie> movie=moiveByTimeAndType.getMovieData(movieTime,jedisTypeId,Integer.parseInt(page));
		//int Count=moiveByTimeAndType.getDataCount();
		int count =moiveByTimeAndType.getDataCount(movieTime,jedisTypeId);
		return movie;
	}
	
	@RequestMapping(value="/setall/qingbuyaoyunxin/xiexie",method=RequestMethod.GET)
	@ResponseBody
	public String setAllMovieType() {
		
		movieUtilsService.newMovieType();
		return "access";
	}
	
	
	
	
}
