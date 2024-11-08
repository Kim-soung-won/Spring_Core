package com.web.core;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.event.EventListener;

import java.util.Date;
import java.util.TimeZone;

@Slf4j
@SpringBootApplication
@EnableFeignClients(basePackages = "com.web.core.client")
public class DashBoardApplication {
    public static void main(String[] args) {
        SpringApplication.run(DashBoardApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void init(){
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Seoul"));
        log.info("Spring boot application running in Asia/Seoul timezone : " + new Date());
    }
}