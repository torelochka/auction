package ru.itis.akchurina.auction.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping({"/main", "/index", "/"})
    public String getMainPage(Model model) {
        return "main";
    }
}
