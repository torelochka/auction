package ru.itis.akchurina.auction.services;

import org.springframework.data.domain.Pageable;
import ru.itis.akchurina.auction.dto.AuctionDto;
import ru.itis.akchurina.auction.dto.BetDto;
import ru.itis.akchurina.auction.forms.AuctionChangeForm;
import ru.itis.akchurina.auction.forms.AuctionForm;

import java.util.List;
import java.util.Optional;

public interface AuctionService {
    void createAuction(AuctionDto auctionDto);
    Optional<AuctionDto> getAuctionById(Long id);

    List<AuctionDto> getAllActive(Pageable pageable);

    AuctionDto findById(Long id);

    void updateWinner(AuctionDto auction, List<BetDto> auctionBets);

    Long getAuctionsCount();

    void update(Long id, AuctionChangeForm auctionForm);

    void delete(Long id);
}
