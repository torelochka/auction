package ru.itis.akchurina.auction.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.akchurina.auction.models.Auction;

import java.util.UUID;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {

}
