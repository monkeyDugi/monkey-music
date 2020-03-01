package com.dugi.monkey.scheduler;

import com.dugi.monkey.domain.music.service.DailyChartsService;
import org.springframework.context.ApplicationContext;

public class DailyChartsBeanUtil {

    public static DailyChartsService getBean(Class<DailyChartsService> beanName) {
        ApplicationContext applicationContext = ApplicationContextProvider.getApplicationContext();

        return applicationContext.getBean(beanName);
    }
}
