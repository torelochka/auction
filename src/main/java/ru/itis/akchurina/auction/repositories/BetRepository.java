package ru.itis.akchurina.auction.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.akchurina.auction.models.Auction;
import ru.itis.akchurina.auction.models.Bet;

import java.util.List;

@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {
    List<Bet> findAllByAuctionOrderByPriceDesc(Auction auction);
}
