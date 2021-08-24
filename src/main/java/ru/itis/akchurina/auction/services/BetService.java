package ru.itis.akchurina.auction.services;

import ru.itis.akchurina.auction.dto.AuctionDto;
import ru.itis.akchurina.auction.dto.BetDto;

import java.util.List;

public interface BetService {
    boolean addBet(BetDto betDto);
    List<BetDto> getAuctionBets(AuctionDto auctionDto);
}
