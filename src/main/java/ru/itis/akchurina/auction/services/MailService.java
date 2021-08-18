package ru.itis.akchurina.auction.services;

import ru.itis.akchurina.auction.dto.AuctionDto;
import ru.itis.akchurina.auction.dto.UserDto;

public interface MailService {
    void sendWinnerEmail(AuctionDto auctionDto);
}
