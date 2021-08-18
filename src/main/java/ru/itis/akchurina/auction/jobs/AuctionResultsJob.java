package ru.itis.akchurina.auction.jobs;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.akchurina.auction.dto.AuctionDto;
import ru.itis.akchurina.auction.services.AuctionService;
import ru.itis.akchurina.auction.services.MailService;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

@Component
public class AuctionResultsJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap mergedJobDataMap = jobExecutionContext.getMergedJobDataMap();
        // TODO: 12.08.2021 добавить юзеру типа выигранные аукционы
        Long id = (Long) mergedJobDataMap.get("auctionId");
        AuctionService auctionService = (AuctionService) mergedJobDataMap.get("auctionService");
        MailService mailService = (MailService) mergedJobDataMap.get("mailService");
        Optional<AuctionDto> auction = auctionService.getAuctionById(id);
        System.out.println(auction);

        auction.ifPresent(auctionDto -> {
            if(auctionDto.getWinner() != null) {
                mailService.sendWinnerEmail(auctionDto);
            }
        });

    }
}
