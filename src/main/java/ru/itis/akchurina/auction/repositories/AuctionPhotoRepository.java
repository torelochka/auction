package ru.itis.akchurina.auction.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.akchurina.auction.models.AuctionPhoto;

@Repository
public interface AuctionPhotoRepository extends JpaRepository<AuctionPhoto, Long> {

}
