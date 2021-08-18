package ru.itis.akchurina.auction.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.lang.annotation.DeclareAnnotation;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Auction {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @ManyToOne
    private User owner;

    private Double price;

    @OneToOne
    private User winner;

    private Date date;
}
