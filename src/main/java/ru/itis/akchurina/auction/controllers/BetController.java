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
import ru.itis.akchurina.auction.services.CurrencyService;

import java.util.Currency;

@Controller
public class BetController {

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BetService betService;

    @Autowired
    private CurrencyService currencyService;

    @PostMapping("/auction/{auctionId}/bet")
    private String addAuctionBet(@PathVariable Long auctionId, BetForm betForm, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        AuctionDto auctionDto = auctionService.findById(auctionId);

        if (!auctionDto.getActive()) {
            return "redirect:/auction/" + auctionId + "?error";
        }

        BetDto betDto = BetDto.builder()
                .price(betForm.getPrice())
                .user(modelMapper.map(userDetails.getUser(), UserDto.class))
                .auction(auctionDto)
                .build();

        if (betForm.getCurrency().equals("USD")) {
            betDto.setPrice(currencyService.convertCurrencyToRub(betForm.getPrice()));
        }

        if (!betService.addBet(betDto)) {
            return "redirect:/auction/" + auctionId + "?errorBet";
        }

        return "redirect:/auction/" + auctionId;
    }
}
