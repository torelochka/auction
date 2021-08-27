package ru.itis.akchurina.auction.services;

import ru.itis.akchurina.auction.dto.UserDto;
import ru.itis.akchurina.auction.forms.UserUpdateForm;

import java.util.Optional;

public interface UserService {
    Optional<UserDto> userByEmail(String email);
    Optional<UserDto> userByEmailAndPassword(String email, String password);

    Optional<UserDto> userById(Long id);

    Optional<UserDto> restUserById(Long id);

    void deleteUserById(Long id);

    Optional<UserDto> updateUser(Long id, UserUpdateForm userUpdateForm);
}
