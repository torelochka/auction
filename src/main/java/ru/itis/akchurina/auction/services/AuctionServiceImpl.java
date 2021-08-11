package ru.itis.akchurina.auction.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.akchurina.auction.dto.AuctionDto;
import ru.itis.akchurina.auction.jobs.SimpleJob;

@Service
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    private JobService jobService;

    @Override
    public void createAuction(AuctionDto auctionDto) {
        // TODO: 12.08.2021 генерировать identify случайно UUID 
        jobService.createJob(SimpleJob.class, auctionDto.getDate(), auctionDto.getUser().getEmail());
    }
}
