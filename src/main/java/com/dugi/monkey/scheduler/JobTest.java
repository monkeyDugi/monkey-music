package com.dugi.monkey.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import java.util.Calendar;

public class JobTest implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        System.out.println("================job 실행 ==========================");
        System.out.println(Calendar.getInstance().getTime());
    }
}
