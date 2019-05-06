package com.sj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sj.show.mapper")
public class StarterMovie {
	public static void main(String[] args) {
		///666666666666666666666666666666666666
		SpringApplication.run(StarterMovie.class, args);
	}
}
