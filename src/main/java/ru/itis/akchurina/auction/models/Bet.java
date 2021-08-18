package ru.itis.akchurina.auction.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Bet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double price;

    @ManyToOne
    private Auction auction;
}

/*<button>Click Me!</button>
        const btn = document.querySelector('button');

function sendData( data ){
    console.log( 'Sending data' );

    const XHR = new XMLHttpRequest()*}
    /
 */
