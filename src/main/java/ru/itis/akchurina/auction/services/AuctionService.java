package ru.itis.akchurina.auction.services;

import ru.itis.akchurina.auction.dto.AuctionDto;
import ru.itis.akchurina.auction.forms.AuctionForm;

import java.util.Optional;
import java.util.UUID;

public interface AuctionService {
    void createAuction(AuctionDto auctionDto);
    Optional<AuctionDto> getAuctionById(Long id);
}
