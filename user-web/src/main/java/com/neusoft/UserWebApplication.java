package com.neusoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;


//@EnableDubbo
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})//阻止spring boot自动注入dataSource bean
public class UserWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserWebApplication.class, args);
    }

}
