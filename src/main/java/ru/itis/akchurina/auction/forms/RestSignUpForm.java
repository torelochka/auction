package ru.itis.akchurina.auction.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.akchurina.auction.validations.annotations.PasswordsMatch;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestSignUpForm {
    private String email;
    private String password;
}
