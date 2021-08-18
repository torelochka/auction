package ru.itis.akchurina.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BetDto {

    private Double price;

    private UserDto user;

    private AuctionDto auction;
}
