package ru.itis.akchurina.auction.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.akchurina.auction.dto.AuctionDto;
import ru.itis.akchurina.auction.dto.BetDto;
import ru.itis.akchurina.auction.dto.UserDto;
import ru.itis.akchurina.auction.forms.BetForm;
import ru.itis.akchurina.auction.security.details.UserDetailsImpl;
import ru.itis.akchurina.auction.services.AuctionService;
import ru.itis.akchurina.auction.services.BetService;

@Controller
public class BetController {

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BetService betService;

    @PostMapping("/auction/{auctionId}/bet")
    private String addAuctionBet(@PathVariable Long auctionId, BetForm betForm, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        AuctionDto auctionDto = auctionService.findById(auctionId);

        BetDto betDto = BetDto.builder()
                .price(betForm.getPrice())
                .user(modelMapper.map(userDetails.getUser(), UserDto.class))
                .auction(auctionDto)
                .build();

        betService.addBet(betDto);

        return "redirect:/auction/" + auctionId;
    }
}
