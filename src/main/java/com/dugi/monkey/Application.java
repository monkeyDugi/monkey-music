package com.dugi.monkey;

import com.dugi.monkey.scheduler.DailyChartScheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 1. 스프링 부트 자동설정 => 스프링 Bean 읽기, 생성 자동
// 2. 이것이 위치한 곳 부터 설정을 읽기 때문에 항상 프로젝트 최상단에 위치한다.
@SpringBootApplication
public class Application {

    @Autowired
    private DailyChartScheduler scheduler;

    public static void main(String[] args) {
        // SpringApplication.run : 내장 WAS(톰캣)
        SpringApplication.run(Application.class, args);
    }
}
