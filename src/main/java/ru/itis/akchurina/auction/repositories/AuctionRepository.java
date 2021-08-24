package ru.itis.akchurina.auction.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.itis.akchurina.auction.models.Auction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {

    @Query(value = "select * from auction where date >= now()", nativeQuery = true)
    List<Auction> findAllActive(Pageable pageable);

    @Query(value = "select count(*) from auction where date >= now()", nativeQuery = true)
    Long auctionCount();
}
