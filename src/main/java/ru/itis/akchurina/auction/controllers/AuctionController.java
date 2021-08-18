package ru.itis.akchurina.auction.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.akchurina.auction.dto.AuctionDto;
import ru.itis.akchurina.auction.dto.UserDto;
import ru.itis.akchurina.auction.forms.AuctionForm;
import ru.itis.akchurina.auction.models.User;
import ru.itis.akchurina.auction.security.details.UserDetailsImpl;
import ru.itis.akchurina.auction.services.AuctionService;

@Controller
public class AuctionController {

    @Autowired
    private AuctionService auctionService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/auction")
    public String createAuction(AuctionForm auctionForm, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = userDetails.getUser();

        AuctionDto auctionDto = modelMapper.map(auctionForm, AuctionDto.class);
        auctionDto.setOwner(modelMapper.map(user, UserDto.class));

        auctionService.createAuction(auctionDto);

        return "redirect:/profile";
    }

    @GetMapping("/auction")
    public String getAuctionCreatePage() {
        return "create_auction";
    }
}
