/*
* - 스케줄러가 에서 Service 보다 빈이 먼저 등록되어 주입이 불가 하다.
* - Service 빈을 찾지 못한다.
* - ApplicationContextAware를 구현 받아 빈이 생성 되는 시점에 진입할 수 있다.
*   진입을 하여 해당 ApplicationContext를 받고, Service 빈을 직접 주입 시킨다.
* - 빈 주입은 애플리케이션 실행 시 한 번만 생성 되므로, static을 사용했다.
* */
package com.dugi.monkey.scheduler;

import com.dugi.monkey.crawling.youtube.dailychart.MelonYoutubeCombination;
import com.dugi.monkey.service.DailyChartService;
import com.dugi.monkey.util.DailyChartBeanUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class DailyChartJob implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        DailyChartService dailyChartService = (DailyChartService) DailyChartBeanUtil.getBean("dailyChartService");
        MelonYoutubeCombination melonYoutubeCombination = (MelonYoutubeCombination) DailyChartBeanUtil.getBean("melonYoutubeCombination");
//        dailyChartService.addDailyChart(melonYoutubeCombination.dailyChart());
    }
}
