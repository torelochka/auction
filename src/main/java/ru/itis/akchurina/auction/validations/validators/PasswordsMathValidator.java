package ru.itis.akchurina.auction.validations.validators;

import ru.itis.akchurina.auction.forms.SignUpForm;
import ru.itis.akchurina.auction.validations.annotations.PasswordsMatch;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordsMathValidator implements ConstraintValidator<PasswordsMatch, SignUpForm> {

    @Override
    public boolean isValid(SignUpForm signUpForm, ConstraintValidatorContext constraintValidatorContext) {
        return signUpForm.getPasswordAgain().equals(signUpForm.getPassword());
    }
}
