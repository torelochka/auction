package ru.itis.akchurina.auction.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.akchurina.auction.forms.RestSignUpForm;
import ru.itis.akchurina.auction.forms.SignUpForm;
import ru.itis.akchurina.auction.models.User;
import ru.itis.akchurina.auction.repositories.UserRepository;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Boolean signUp(SignUpForm signUpForm) {
        if (userRepository.findByEmail(signUpForm.getEmail()).isPresent()) {
            return false;
        }

        User user = User.builder()
                .email(signUpForm.getEmail())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .build();

        userRepository.save(user);

        return true;
    }

    @Override
    public Boolean signUp(RestSignUpForm signUpForm) {
        return signUp(SignUpForm.builder()
                .email(signUpForm.getEmail())
                .password(signUpForm.getPassword())
                .passwordAgain(signUpForm.getPassword())
                .build()
        );
    }
}
