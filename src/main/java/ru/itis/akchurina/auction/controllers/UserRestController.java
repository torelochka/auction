package ru.itis.akchurina.auction.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.akchurina.auction.dto.UserDto;
import ru.itis.akchurina.auction.forms.RestSignUpForm;
import ru.itis.akchurina.auction.forms.SignUpForm;
import ru.itis.akchurina.auction.forms.UserUpdateForm;
import ru.itis.akchurina.auction.services.SignUpService;
import ru.itis.akchurina.auction.services.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/rest")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private SignUpService signUpService;

    @GetMapping("/user/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        return ResponseEntity.of(userService.restUserById(id));
    }

    @PostMapping("/user")
    public ResponseEntity<UserDto> saveUser(@RequestBody RestSignUpForm signUpForm) {
         if (signUpService.signUp(signUpForm)) {
             return ResponseEntity.of(userService.userByEmail(signUpForm.getEmail()));
         }

         return ResponseEntity.of(Optional.empty());
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserUpdateForm userUpdateForm) {
        return ResponseEntity.of(userService.updateUser(id, userUpdateForm));
    }
}
