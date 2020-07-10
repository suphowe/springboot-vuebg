package com.soft;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.RestController;

@MapperScan("com.soft.mapper")
@RestController
@SpringBootApplication
public class SpringbootVuebgApplication {

    private static final Logger logger = LoggerFactory.getLogger(SpringbootVuebgApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringbootVuebgApplication.class, args);
        logger.info("========================启动完毕========================");
    }

    protected SpringApplicationBuilder configure(SpringApplicationBuilder application){
        return application.sources(SpringbootVuebgApplication.class);
    }

}
