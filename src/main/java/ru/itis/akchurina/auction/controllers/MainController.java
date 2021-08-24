package ru.itis.akchurina.auction.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.akchurina.auction.dto.AuctionDto;
import ru.itis.akchurina.auction.services.AuctionService;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    private AuctionService auctionService;

    private final int pageSize = 4;

    @GetMapping({"/main", "/index", "/"})
    public String getMainPage(Model model, @PageableDefault(size = pageSize)Pageable pageable) {
        List<AuctionDto> allActive = auctionService.getAllAuctions(pageable);

        long auctionCount = Math.round(auctionService.getAuctionsCount() / (double) pageSize);

        model.addAttribute("count", auctionCount);

        System.out.println(auctionCount);

        model.addAttribute("auctions", allActive);

        return "main";
    }

    @GetMapping(value = "/more/auction", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<AuctionDto> getMoreAuction(Pageable pageable) {
        return auctionService.getAllAuctions(pageable);
    }
}
