package ru.itis.akchurina.auction.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.akchurina.auction.dto.AuctionDto;
import ru.itis.akchurina.auction.dto.BetDto;
import ru.itis.akchurina.auction.models.Auction;
import ru.itis.akchurina.auction.models.Bet;
import ru.itis.akchurina.auction.repositories.BetRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BetServiceImpl implements BetService {

    @Autowired
    private BetRepository betRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AuctionService auctionService;

    @Override
    public boolean addBet(BetDto betDto) {
        Bet bet = modelMapper.map(betDto, Bet.class);

        AuctionDto auction = modelMapper.map(bet.getAuction(), AuctionDto.class);
        if (getAuctionBets(auction).contains(betDto)) {
            return false;
        }

        betRepository.save(bet);

        if (bet.getPrice() >= auction.getPrice()) {
            auctionService.closeAuction(auction);
        }

        return true;
    }

    @Override
    public List<BetDto> getAuctionBets(AuctionDto auctionDto) {
        return betRepository.findAllByAuctionOrderByPriceDesc(modelMapper.map(auctionDto, Auction.class))
                .stream().map(bet -> modelMapper.map(bet, BetDto.class))
                .collect(Collectors.toList());
    }
}
