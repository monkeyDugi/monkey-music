package com.dugi.monkey.util;

import org.springframework.context.ApplicationContext;

public class DailyChartBeanUtil {

    public static Object getBean(String beanName) {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();

        return applicationContext.getBean(beanName);
    }
}
