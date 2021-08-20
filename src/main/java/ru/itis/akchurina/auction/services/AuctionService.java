package ru.itis.akchurina.auction.services;

import ru.itis.akchurina.auction.dto.AuctionDto;
import ru.itis.akchurina.auction.dto.BetDto;

import java.util.List;
import java.util.Optional;

public interface AuctionService {
    void createAuction(AuctionDto auctionDto);
    Optional<AuctionDto> getAuctionById(Long id);

    List<AuctionDto> getAllActive();

    AuctionDto findById(Long id);

    void updateWinner(AuctionDto auction, List<BetDto> auctionBets);
}
