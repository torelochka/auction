package ru.itis.akchurina.auction.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.akchurina.auction.dto.AuctionDto;
import ru.itis.akchurina.auction.dto.AuctionPhotoDto;
import ru.itis.akchurina.auction.jobs.AuctionResultsJob;
import ru.itis.akchurina.auction.models.Auction;
import ru.itis.akchurina.auction.models.AuctionPhoto;
import ru.itis.akchurina.auction.models.User;
import ru.itis.akchurina.auction.repositories.AuctionPhotoRepository;
import ru.itis.akchurina.auction.repositories.AuctionRepository;
import ru.itis.akchurina.auction.utils.FileSystemStorageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    private JobService jobService;

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private AuctionPhotoRepository auctionPhotoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public void createAuction(AuctionDto auctionDto) {

        Auction auction = modelMapper.map(auctionDto, Auction.class);
        List<AuctionPhoto> auctionPhotos = new ArrayList<>();

        for (AuctionPhotoDto photo : auctionDto.getPhotosName()) {
            AuctionPhoto auctionPhoto = AuctionPhoto.builder()
                    .photoName(photo.getPhotoName())
                    .build();

            auctionPhotoRepository.save(auctionPhoto);

            auctionPhotos.add(auctionPhoto);
        }

        auction.setOwner(modelMapper.map(auctionDto.getOwner(), User.class));
        auction.setPhotosName(auctionPhotos);

        Long auctionId = auctionRepository.save(auction).getId();

        jobService.createJob(AuctionResultsJob.class, auctionDto.getDate(), auctionId);
    }

    @Override
    public Optional<AuctionDto> getAuctionById(Long id) {
        return auctionRepository.findById(id)
                .map(auction -> modelMapper.map(auction, AuctionDto.class));
    }

    @Override
    public List<AuctionDto> getAllActive() {
        return auctionRepository.findAllActive().stream()
                .map(auction -> modelMapper.map(auction, AuctionDto.class))
                .collect(Collectors.toList());
    }
}
