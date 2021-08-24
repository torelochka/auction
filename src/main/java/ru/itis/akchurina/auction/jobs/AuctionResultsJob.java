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

@Component
public class AuctionResultsJob implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap mergedJobDataMap = jobExecutionContext.getMergedJobDataMap();
        Long id = (Long) mergedJobDataMap.get("auctionId");
        AuctionService auctionService = (AuctionService) mergedJobDataMap.get("auctionService");
        MailService mailService = (MailService) mergedJobDataMap.get("mailService");
        Optional<AuctionDto> auction = auctionService.getAuctionById(id);
        auctionService.closeAuction(auction.get());
        auction = auctionService.getAuctionById(id);

        auction.ifPresent(auctionDto -> {
            if(auctionDto.getWinner() != null) {
                mailService.sendWinnerEmail(auctionDto);
            }
        });

    }
}
