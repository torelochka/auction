package ru.itis.akchurina.auction.services;

import ru.itis.akchurina.auction.dto.AuctionDto;
import ru.itis.akchurina.auction.forms.AuctionForm;

public interface AuctionService {
    void createAuction(AuctionDto auctionDto);
}
