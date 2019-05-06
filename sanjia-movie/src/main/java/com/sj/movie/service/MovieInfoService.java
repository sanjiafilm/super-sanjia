package com.sj.movie.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.sj.common.pojo.Movie;
import com.sj.movie.mapper.MovieInfoMapper;
@Service
public class MovieInfoService {
	
	@Resource
	private MovieInfoMapper movieInfoMapper;
	public Movie getMovieInfo(String movieName,Integer page) {
		try {
			//获取当前时间
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    Calendar calendar = Calendar.getInstance();
		    Date dayOneUtil = calendar.getTime();
		    System.out.println(dayOneUtil.toString());
		    java.sql.Timestamp dayOneSql=new java.sql.Timestamp(dayOneUtil.getTime());
		    System.out.println(dayOneSql);
		    //获取第二天时间
		    Calendar cal = Calendar.getInstance();
	        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
	        Date beginOfDate = cal.getTime();
	        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String format = formatter.format(beginOfDate);
	        Date parse = formatter.parse(format);
	        Calendar c = Calendar.getInstance();  
	        c.setTime(parse);  
	        c.add(Calendar.DAY_OF_MONTH, 1);
	        Date dayTwoUtil = c.getTime();
	        System.out.println(dayTwoUtil.toString());
	        java.sql.Timestamp dayTwoSql=new java.sql.Timestamp(dayTwoUtil.getTime());
			System.out.println(dayTwoSql);
			 Movie dataMapper=null;
			//获取数据
			if(page==1) {
				dataMapper = movieInfoMapper.getMovieInfoByName(movieName, dayOneSql, dayTwoSql,0);
			}else {
				int pagei=(page-1)*5;
				Integer pageGer=new Integer(pagei);
		        dataMapper = movieInfoMapper.getMovieInfoByName(movieName, dayOneSql, dayTwoSql,pagei);
			}
			
			/*dataString=gradeMapper.getGradeName(0,j);
			System.out.println(dataString);
			dataMapper = gradeMapper.getGradeData(dataString,dayOne,dayTwo);
			list.add(dataMapper);*/
			return dataMapper;
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("报错了");
			return null;
		}
	}
	public Integer getMovieInfoCount(String name) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Calendar calendar = Calendar.getInstance();
	    Date dayOneUtil = calendar.getTime();
	    System.out.println(dayOneUtil.toString());
	    java.sql.Timestamp dayOneSql=new java.sql.Timestamp(dayOneUtil.getTime());
	    System.out.println(dayOneSql);
	    //获取第二天时间
	    Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        Date beginOfDate = cal.getTime();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = formatter.format(beginOfDate);
        Date parse;
		try {
			parse = formatter.parse(format);
			 Calendar c = Calendar.getInstance();  
		        c.setTime(parse);  
		        c.add(Calendar.DAY_OF_MONTH, 1);
		        Date dayTwoUtil = c.getTime();
		       // System.out.println(dayTwoUtil.toString());
		        java.sql.Timestamp dayTwoSql=new java.sql.Timestamp(dayTwoUtil.getTime());
				//System.out.println(dayTwoSql);
		    	Integer i= movieInfoMapper.getInfoCount(name,dayOneSql, dayTwoSql);
		    	return i;
				
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
       
		
		
		
		
		
		

		
	}
	

}
