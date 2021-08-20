package ru.itis.akchurina.auction.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.akchurina.auction.dto.AuctionDto;
import ru.itis.akchurina.auction.services.AuctionService;
import ru.itis.akchurina.auction.services.BetService;

@Controller
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private BetService betService;

    @GetMapping("/auction/{id}")
    public String getAuctionPage(@PathVariable Long id, Model model) {
        AuctionDto auction = auctionService.findById(id);
        model.addAttribute("auction", auction);

        model.addAttribute("bets", betService.getAuctionBets(auction));

        // TODO: 18.08.2021 выводить ставки с сортировкой
        
        return "auction";
    }
}
