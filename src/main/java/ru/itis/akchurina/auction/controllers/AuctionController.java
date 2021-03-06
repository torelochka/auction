package ru.itis.akchurina.auction.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String getAuctionPage(@PathVariable Long id, Model model, @RequestParam(required = false) String error, @RequestParam(required = false) String errorBet) {
        if (error != null) {
            model.addAttribute("error", "Аукцион уже закончился");
        }

        if (errorBet != null) {
            model.addAttribute("errorBet", "Ставка такого номинала уже сделана");
        }

        AuctionDto auction = auctionService.findById(id);
        model.addAttribute("auction", auction);

        model.addAttribute("bets", betService.getAuctionBets(auction));

        return "auction";
    }
}
