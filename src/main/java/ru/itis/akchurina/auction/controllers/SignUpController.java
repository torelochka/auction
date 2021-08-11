package ru.itis.akchurina.auction.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.akchurina.auction.forms.SignUpForm;
import ru.itis.akchurina.auction.services.SignUpService;

import javax.validation.Valid;
import java.util.Objects;

@Controller
public class SignUpController {

    private final SignUpService signUpService;

    @Autowired
    public SignUpController(SignUpService signUpService) {
        this.signUpService = signUpService;
    }

    @GetMapping("/signUp")
    public String getSignUpPage(@RequestParam(required = false) String error, Model model) {
        model.addAttribute("signUpForm", new SignUpForm());
        model.addAttribute("error", error);

        return "sign_up";
    }

    @PostMapping("/signUp")
    public String signUp(@Valid SignUpForm form, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                if (Objects.requireNonNull(error.getCodes())[0].equals("PasswordsMatch.signUpForm")) {
                    model.addAttribute("passwordsErrorMessage", error.getDefaultMessage());
                }
            });
            model.addAttribute("signUpForm", form);
            return "sign_up";
        }

        if (signUpService.signUp(form)) {
            return "redirect:/signIn";
        }

        model.addAttribute("error", "Пользователь с такой почтой уже существует");
        return "redirect:/signUp?error";
    }
}
// TODO: 07.08.2021 добавить валидацию на совпадение паролей при регистрации, добавить проверку почты