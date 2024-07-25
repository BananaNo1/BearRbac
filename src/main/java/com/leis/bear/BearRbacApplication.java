package com.leis.bear;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.leis.bear.mapper")
public class BearRbacApplication {

    public static void main(String[] args) {
        SpringApplication.run(BearRbacApplication.class, args);
    }

}
