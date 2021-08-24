package ru.itis.akchurina.auction.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.akchurina.auction.dto.AuctionDto;
import ru.itis.akchurina.auction.forms.AuctionChangeForm;
import ru.itis.akchurina.auction.forms.AuctionForm;
import ru.itis.akchurina.auction.security.details.UserDetailsImpl;
import ru.itis.akchurina.auction.services.AuctionService;

@Controller
public class AuctionChangeController {

    @Autowired
    private AuctionService auctionService;

    @GetMapping("/auction/{id}/change")
    public String auctionChangePage(@PathVariable Long id, Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        AuctionDto auction = auctionService.findById(id);

        if (!userDetails.getUser().getId().equals(auction.getOwner().getId())) {
            return "redirect:/main";
        }

        model.addAttribute("auction", auction);

        return "change_auction";
    }

    @PostMapping("/auction/{id}/change")
    public String auctionChange(@PathVariable Long id, AuctionChangeForm auctionForm, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        AuctionDto auction = auctionService.findById(id);

        if (!userDetails.getUser().getId().equals(auction.getOwner().getId())) {
            return "redirect:/main";
        }

        auctionService.update(id, auctionForm);

        return "redirect:/auction/" + id;
    }
}
