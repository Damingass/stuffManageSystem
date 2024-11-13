package com.stuffmanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.stuffmanage.*.mapper")
public class StuffManageApplication {

    public static void main(String[] args) {
        SpringApplication.run(StuffManageApplication.class, args);
    }

}
