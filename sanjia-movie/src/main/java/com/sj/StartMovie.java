package com.sj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
//@MapperScan(value="com.sj.movie.mapper")
public class StartMovie {
	public static void main(String[] args) {
		SpringApplication.run(StartMovie.class, args);
	}
}