package com.sj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan(basePackages = "com.sj.movie.mapper")
public class StarterMovie {
	public static void main(String[] args) {
		SpringApplication.run(StarterMovie.class);
	}
}
