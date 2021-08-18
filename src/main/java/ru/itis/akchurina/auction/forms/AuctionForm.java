package ru.itis.akchurina.auction.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.FutureOrPresent;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuctionForm {

    @FutureOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date date;

    private String title;
    private Double price;

    private String description;

    private List<MultipartFile> photos;
}
