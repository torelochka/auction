package ru.itis.akchurina.auction.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuctionDto {

    private Long id;
    private Double price;
    private UserDto owner;
    private String title;
    private Date date;
}
