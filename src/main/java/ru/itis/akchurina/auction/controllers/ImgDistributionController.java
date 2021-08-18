package ru.itis.akchurina.auction.controllers;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.akchurina.auction.utils.FileSystemStorageService;

import java.io.InputStream;

@RestController
public class ImgDistributionController {

    @Autowired
    private FileSystemStorageService storageService;

    @SneakyThrows
    @GetMapping(value = "/dist/img/{name}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable String name) {
        Resource file = storageService.loadAsResource(name);

        InputStream in = file.getInputStream();

        byte[] byteImage = new byte[in.available()];
        in.read(byteImage);

        return byteImage;
    }
}
