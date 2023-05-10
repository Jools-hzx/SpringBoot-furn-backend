package com.hspedu.furn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Zexi He.
 * @date 2023/5/9 18:35
 * @description:
 */
@MapperScan(basePackages = "com.hspedu.furn.mapper")
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
