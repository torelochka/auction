package ru.itis.akchurina.auction.services;

import ru.itis.akchurina.auction.forms.RestSignUpForm;
import ru.itis.akchurina.auction.forms.SignUpForm;

public interface SignUpService {
    Boolean signUp(SignUpForm signUpForm);
    Boolean signUp(RestSignUpForm signUpForm);
}
