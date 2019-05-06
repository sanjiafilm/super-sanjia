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
import com.sj.movie.entity.MovieVo;
import com.sj.movie.mapper.TimeAndTypeMapper;
@Service
public class MoiveByTimeAndType {
	@Resource
	private TimeAndTypeMapper timeAndTypeMapper;
	public List<Movie> getMovieData(String movieTime, List<String> jedisTypeId,Integer page) {
		try {
			
			MovieVo jedisType=new MovieVo();
			List<Integer> IntegerType=new ArrayList<>();
			//将获取到的ID装换为Integer类型
			for (String integer : jedisTypeId) {
				IntegerType.add(Integer.parseInt(integer));
			}
			
			jedisType.setJedisType(IntegerType);
			
			List<String> movies=null;
			//获取到id对应的name字段
			/*if(page==1) {
				jedisType.setPage(0);
				movies=timeAndTypeMapper.getMovieName(jedisType);
				
			}else {
				int pageint=page*3-3;
				jedisType.setPage(pageint);
				movies=timeAndTypeMapper.getMovieName(jedisType);
			
			}*/
			
			movies=timeAndTypeMapper.getMovieName(jedisType);
			
			//获取所选时间
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dateOne=df.parse(movieTime);
			
			 java.sql.Timestamp dayOneSql=new java.sql.Timestamp(dateOne.getTime());
			//获取所选时间第二天
			
			Integer time=Integer.parseInt(movieTime.split(" ")[0].split("-")[2])+1;
			String[] str1=movieTime.split(" ");
			String[] str2=str1[0].split("-");
			
			
			String dateTwoStr=str2[0]+"-"+str2[1]+"-"+time.toString()+" "+str1[1];
			System.out.println(dateTwoStr);
			Date dateTwo=df.parse(dateTwoStr);
			
			 java.sql.Timestamp dayTwoSql=new java.sql.Timestamp(dateTwo.getTime());
			 
			 //查询数据
			 //获取到全部数据
			 List<Movie> listAll=new ArrayList<>();
			 Movie dataMapper;
			 for (String movie : movies) {
				 dataMapper=timeAndTypeMapper.getMovieData(movie,dayOneSql,dayTwoSql);
				 if(dataMapper!=null) {
					 listAll.add(dataMapper);
				 }
				
			}
			 System.out.println(listAll.size());
			//显示要输出的数据
			 List<Movie> list=new ArrayList<>();
			 if(page==1) {
				for(int i=0;i<3;i++) {
					list.add(listAll.get(i));
				}
			 }else {
				 int num=3*page;
				 for(int i=num-3;i<num;i++) {
					 if(i==listAll.size()) {
						 break;
					 }
						list.add(listAll.get(i));
					}
			 }

			return list;
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public int getDataCount(String movieTime,List<String> jedisTypeId) {
		MovieVo jedisType=new MovieVo();
		List<Integer> IntegerType=new ArrayList<>();
		//将获取到的ID装换为Integer类型
		for (String integer : jedisTypeId) {
			IntegerType.add(Integer.parseInt(integer));
		}
		
		jedisType.setJedisType(IntegerType);
		
		List<String> movies=null;
		//获取到id对应的name字段
		/*if(page==1) {
			jedisType.setPage(0);
			movies=timeAndTypeMapper.getMovieName(jedisType);
			
		}else {
			int pageint=page*3-3;
			jedisType.setPage(pageint);
			movies=timeAndTypeMapper.getMovieName(jedisType);
		
		}*/
		
		movies=timeAndTypeMapper.getMovieName(jedisType);
		
		//获取所选时间
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date dateOne;
		try {
			dateOne = df.parse(movieTime);
			 java.sql.Timestamp dayOneSql=new java.sql.Timestamp(dateOne.getTime());
				//获取所选时间第二天
				
				Integer time=Integer.parseInt(movieTime.split(" ")[0].split("-")[2])+1;
				String[] str1=movieTime.split(" ");
				String[] str2=str1[0].split("-");
				
				
				String dateTwoStr=str2[0]+"-"+str2[1]+"-"+time.toString()+" "+str1[1];
				System.out.println(dateTwoStr);
				Date dateTwo=df.parse(dateTwoStr);
				
				 java.sql.Timestamp dayTwoSql=new java.sql.Timestamp(dateTwo.getTime());
				 
				 //查询数据
				 //获取到全部数据
				 List<Movie> listAll=new ArrayList<>();
				 Movie dataMapper;
				 for (String movie : movies) {
					 dataMapper=timeAndTypeMapper.getMovieData(movie,dayOneSql,dayTwoSql);
					 if(dataMapper!=null) {
						 listAll.add(dataMapper);
					 }
					
				}
				// System.out.println(listAll.size());
				return listAll.size();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		
		
	}

}
