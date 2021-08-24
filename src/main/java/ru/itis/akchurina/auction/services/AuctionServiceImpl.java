package ru.itis.akchurina.auction.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.itis.akchurina.auction.dto.AuctionDto;
import ru.itis.akchurina.auction.dto.AuctionPhotoDto;
import ru.itis.akchurina.auction.dto.BetDto;
import ru.itis.akchurina.auction.dto.UserDto;
import ru.itis.akchurina.auction.forms.AuctionChangeForm;
import ru.itis.akchurina.auction.forms.AuctionForm;
import ru.itis.akchurina.auction.jobs.AuctionResultsJob;
import ru.itis.akchurina.auction.models.Auction;
import ru.itis.akchurina.auction.models.AuctionPhoto;
import ru.itis.akchurina.auction.models.Bet;
import ru.itis.akchurina.auction.models.User;
import ru.itis.akchurina.auction.repositories.AuctionPhotoRepository;
import ru.itis.akchurina.auction.repositories.AuctionRepository;
import ru.itis.akchurina.auction.repositories.BetRepository;
import ru.itis.akchurina.auction.repositories.UserRepository;

import java.util.*;
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

    @Autowired
    private UserRepository userRepository;

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
    public List<AuctionDto> getAllActive(Pageable pageable) {
        return auctionRepository.findAllActive(pageable).stream()
                .map(auction -> modelMapper.map(auction, AuctionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AuctionDto findById(Long id) {
        return auctionRepository.findById(id)
                .map(auction -> modelMapper.map(auction, AuctionDto.class))
                .get();
    }

    @Override
    public void updateWinner(AuctionDto auctionDto, List<BetDto> auctionBets) {
        Auction auction = auctionRepository.findById(auctionDto.getId()).get();

        Double maxPrice = 0D;

        for (BetDto betDto : auctionBets) {
            if (betDto.getPrice() > maxPrice) {
                auction.setWinner(modelMapper.map(betDto.getUser(), User.class));
                maxPrice = betDto.getPrice();
            }
        }

        if (maxPrice >= auction.getPrice()) {
            auction.setActive(false);
            jobService.triggerJob(auction.getId());
        }

        auctionRepository.save(auction);
    }

    @Override
    public Long getAuctionsCount() {
        return auctionRepository.auctionCount();
    }

    @Override
    public void update(Long id, AuctionChangeForm auctionForm) {
        Auction auction = auctionRepository.findById(id).get();

        auction.setDate(auctionForm.getDate());
        auction.setDescription(auctionForm.getDescription());
        auction.setPrice(auctionForm.getPrice());
        auction.setTitle(auctionForm.getTitle());

        auctionRepository.save(auction);
    }

    @Override
    public void delete(Long id) {
        auctionRepository.deleteById(id);
    }

    @Override
    public List<AuctionDto> getUserWonAuctions(Long id) {
        User user = userRepository.findById(id).get();

        return auctionRepository.findAllByWinner(user).stream()
                .map(auction -> modelMapper.map(auction, AuctionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AuctionDto> getUserCurrentAuctions(Long id) {
        User user = userRepository.findById(id).get();

        return auctionRepository.findAllUserCurrentAuction(user.getId()).stream()
                .map(auction -> modelMapper.map(auction, AuctionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<AuctionDto> getUserAuctions(Long id) {
        User user = userRepository.findById(id).get();

        return auctionRepository.findAllByOwner(user).stream()
                .map(auction -> modelMapper.map(auction, AuctionDto.class))
                .collect(Collectors.toList());
    }

}
