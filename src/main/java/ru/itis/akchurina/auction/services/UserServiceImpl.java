package ru.itis.akchurina.auction.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.akchurina.auction.dto.UserDto;
import ru.itis.akchurina.auction.forms.UserUpdateForm;
import ru.itis.akchurina.auction.models.User;
import ru.itis.akchurina.auction.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Optional<UserDto> userByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(user -> modelMapper.map(user, UserDto.class));
    }

    @Override
    public Optional<UserDto> userByEmailAndPassword(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        if (user.isPresent() && passwordEncoder.matches(password, user.get().getPassword())) {
            return user.map(u -> modelMapper.map(u, UserDto.class));
        }

        return Optional.empty();
    }

    @Override
    public Optional<UserDto> userById(Long id) {
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user, UserDto.class));
    }

    @Override
    public Optional<UserDto> restUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getState().equals(User.State.STATE_BANNED)) {
                return Optional.empty();
            }
        }
        return userOptional.map(user1 -> modelMapper.map(user1, UserDto.class));
    }

    @Override
    public void deleteUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setState(User.State.STATE_BANNED);
            userRepository.save(user);
        }
    }

    @Override
    public Optional<UserDto> updateUser(Long id, UserUpdateForm userUpdateForm) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isPresent() && optionalUser.get().getState().equals(User.State.STATE_ACTIVE)) {
            User user = optionalUser.get();
            user.setEmail(userUpdateForm.getEmail());
            return Optional.of(userRepository.save(user)).map(user1 -> modelMapper.map(user1, UserDto.class));
        }

        return Optional.empty();
    }
}
