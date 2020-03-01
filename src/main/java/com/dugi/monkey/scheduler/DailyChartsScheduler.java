package com.dugi.monkey.scheduler;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DailyChartsScheduler {

    @PostConstruct // 해당 클래스가 Bean 등록 되자마다 실행(인스턴스화 되자마자)
                   // 스케쥴러를 통한 배치 이기 때문에 어디선가 메서드를 호출해 실행하는 것 보다는
                   // 이렇게 자동으로 실행되게 하는 것이 좋다.
    public void start() throws SchedulerException {

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        scheduler.start(); // 스케줄러 시작!

        // Job 지정
        JobDetail job = JobBuilder.newJob(DailyChartsJob.class) // 실행할 Job 클래스
                                .withIdentity("dailyChartsJob") // Job 고유명 지정
                                .build();

        // trgger 생성
        // 지정 시간에 trigger GO GO!!
        Trigger trigger = TriggerBuilder.newTrigger()
                                        .withSchedule(CronScheduleBuilder.cronSchedule("15 * * * * ?"))
                                        .build();

        scheduler.scheduleJob(job, trigger);
    }
}
