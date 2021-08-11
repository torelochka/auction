package ru.itis.akchurina.auction.jobs;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class SimpleJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap mergedJobDataMap = jobExecutionContext.getMergedJobDataMap();
        // TODO: 12.08.2021 отправка на почту
        // TODO: 12.08.2021 добавить юзеру типа выигранные аукционы
        System.out.println(mergedJobDataMap.get("email"));
        System.out.println("JOB SUCCESS");
    }
}
