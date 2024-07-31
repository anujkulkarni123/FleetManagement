package com.exavalu.fleetmanagementapp;

import org.springframework.boot.SpringApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.exavalu.fleetmanagementapp.repositories")
public class FleetmanagementappApplication {

    public static void main(String[] args) {
        SpringApplication.run(FleetmanagementappApplication.class, args);
    }
}
