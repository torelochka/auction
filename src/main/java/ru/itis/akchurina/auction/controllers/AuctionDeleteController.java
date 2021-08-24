package ru.itis.akchurina.auction.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.itis.akchurina.auction.dto.AuctionDto;
import ru.itis.akchurina.auction.security.details.UserDetailsImpl;
import ru.itis.akchurina.auction.services.AuctionService;

@Controller
public class AuctionDeleteController {

    @Autowired
    private AuctionService auctionService;

    @GetMapping("/auction/{id}/delete")
    public String deleteAuction(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        AuctionDto auction = auctionService.findById(id);

        if (!userDetails.getUser().getId().equals(auction.getOwner().getId())) {
            return "redirect:/main";
        }

        auctionService.delete(id);

        return "redirect:/main";
    }
}
