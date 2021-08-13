package ru.itis.akchurina.auction.jobs;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.akchurina.auction.services.AuctionService;

import java.util.UUID;

@Component
public class AuctionResultsJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap mergedJobDataMap = jobExecutionContext.getMergedJobDataMap();
        // TODO: 12.08.2021 отправка на почту
        // TODO: 12.08.2021 добавить юзеру типа выигранные аукционы
        Long id = (Long) mergedJobDataMap.get("auctionId");
        AuctionService auctionService = (AuctionService) mergedJobDataMap.get("auctionService");
        System.out.println(id);
        System.out.println(auctionService.getAuctionById(id));
        System.out.println("JOB SUCCESS");
    }
}
