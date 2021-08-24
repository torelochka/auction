package ru.itis.akchurina.auction.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.akchurina.auction.models.User;
import ru.itis.akchurina.auction.security.details.UserDetailsImpl;
import ru.itis.akchurina.auction.services.AuctionService;

@Controller
public class ProfileController {

    @Autowired
    private AuctionService auctionService;

    @GetMapping("/profile")
    public String getProfilePage(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        User user = userDetails.getUser();

        model.addAttribute("user", user);

        model.addAttribute("auctionsWon", auctionService.getUserWonAuctions(user.getId()));
        model.addAttribute("currentAuctions", auctionService.getUserCurrentAuctions(user.getId()));
        model.addAttribute("myAuctions", auctionService.getUserAuctions(user.getId()));

        return "profile";
    }
}
