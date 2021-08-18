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

        Auction auction = modelMapper.map(auctionDto, Auction.class);
        auction.setOwner(modelMapper.map(auctionDto.getOwner(), User.class));

        Long auctionId = auctionRepository.save(auction).getId();

        jobService.createJob(AuctionResultsJob.class, auctionDto.getDate(), auctionId);
    }

    @Override
    public Optional<AuctionDto> getAuctionById(Long id) {
        return auctionRepository.findById(id)
                .map(auction -> modelMapper.map(auction, AuctionDto.class));
    }
}
