package com.example.Group;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.example.Group.Entity")
@EnableJpaRepositories(basePackages = "com.example.Group.Repository")
public class GroupApplication {

    public static void main(String[] args) {
        SpringApplication.run(GroupApplication.class, args);
    }

}
