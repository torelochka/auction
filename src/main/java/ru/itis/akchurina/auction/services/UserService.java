package ru.itis.akchurina.auction.services;

import ru.itis.akchurina.auction.dto.UserDto;

import java.util.Optional;

public interface UserService {
    Optional<UserDto> userByEmail(String email);
    Optional<UserDto> userByEmailAndPassword(String email, String password);

    Optional<UserDto> userById(Long id);
}
