package ru.itis.akchurina.auction.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.akchurina.auction.models.AuctionPhoto;

public interface AuctionPhotoRepository extends JpaRepository<AuctionPhoto, Long> {

}
