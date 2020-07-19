package com.dugi.monkey.util;

import org.springframework.context.ApplicationContext;

/**
 * @author : 이병덕
 * @description : 일간차트 스케줄러 실행 시, DailyChartJob.java 에서 "dailyChartService", "melonYoutubeCombination"의 Bean을 가져오기 위해 사용
 * @date : 2020.07.19 22:44:28
 */

public class DailyChartBeanUtil {

    public static Object getBean(String beanName) {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();

        return applicationContext.getBean(beanName);
    }
}
