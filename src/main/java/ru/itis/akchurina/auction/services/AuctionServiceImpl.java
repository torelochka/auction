package ru.itis.akchurina.auction.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.akchurina.auction.dto.AuctionDto;
import ru.itis.akchurina.auction.jobs.AuctionResultsJob;
import ru.itis.akchurina.auction.models.Auction;
import ru.itis.akchurina.auction.models.User;
import ru.itis.akchurina.auction.repositories.AuctionRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    private JobService jobService;
    @Autowired
    private AuctionRepository auctionRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void createAuction(AuctionDto auctionDto) {

        Auction auction = Auction.builder()
                .owner(modelMapper.map(auctionDto.getUser(), User.class))
                .title(auctionDto.getTitle())
                .date(auctionDto.getDate())
                .build();

        Long auctionId = auctionRepository.save(auction).getId();

        jobService.createJob(AuctionResultsJob.class, auctionDto.getDate(), auctionId);
    }

    // TODO: 13.08.2021 почему то user null auctionService.getById() 
    
    @Override
    public Optional<AuctionDto> getAuctionById(Long id) {
        return auctionRepository.findById(id)
                .map(auction -> modelMapper.map(auction, AuctionDto.class));
    }
}
