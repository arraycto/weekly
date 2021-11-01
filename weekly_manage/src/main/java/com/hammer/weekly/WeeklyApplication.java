package com.hammer.weekly;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.hammer.weekly.dao")
public class WeeklyApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeeklyApplication.class, args);
    }

}
