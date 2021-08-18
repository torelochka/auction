package ru.itis.akchurina.auction.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.akchurina.auction.services.AuctionService;

@Controller
public class MainController {

    @Autowired
    private AuctionService auctionService;

    @GetMapping({"/main", "/index", "/"})
    public String getMainPage(Model model) {
        model.addAttribute("auctions", auctionService.getAllActive());

        return "main";

    }
}
