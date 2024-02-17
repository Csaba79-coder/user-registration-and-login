package com.example.userregistrationandlogin.service;

import com.example.userregistrationandlogin.controller.exception.InvalidInputException;
import com.example.userregistrationandlogin.entity.User;
import com.example.userregistrationandlogin.model.UserLoginModel;
import com.example.userregistrationandlogin.model.UserModel;
import com.example.userregistrationandlogin.model.UserRegisterModel;
import com.example.userregistrationandlogin.model.UserUpdateModel;
import com.example.userregistrationandlogin.repository.UserRepository;
import com.example.userregistrationandlogin.util.Mapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<UserModel> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(Mapper::mapUserEntityToUserModel)
                .collect(Collectors.toList());
    }

    @Override
    public UserModel retrieveUserById(Long id) {
        return Mapper.mapUserEntityToUserModel(userRepository.findById(id)
                        .orElseThrow(() -> {
                    String message = String.format("User with id %d not found", id);
                    log.info(message);
                    return new NoSuchElementException(message);
                        }));
    }

    @Override
    public UserModel retrieveUserByEmail(String email) {
        return Mapper.mapUserEntityToUserModel(
                userRepository.findByEmail(email)
                        .orElseThrow(() -> {
                    String message = String.format("User with email %s not found", email);
                    log.info(message);
                    return new NoSuchElementException(message);
                        }));
    }

    @Override
    public UserModel createUser(UserRegisterModel userRegModel) {
        return Mapper.mapUserEntityToUserModel(userRepository.save(Mapper.mapUserRegistrationModelToUserEntity(userRegModel)));
    }

    @Override
    public UserModel updateUser(UserUpdateModel userUpdateModel) {
        Optional<User> userOptionalByEmail = userRepository.findByEmail(userUpdateModel.getEmail());
        if (userOptionalByEmail.isPresent()) {
            User existingUser = userOptionalByEmail.get();
            if (userUpdateModel.getUsername() != null && !userUpdateModel.getUsername().equals(existingUser.getUsername())) {
                existingUser.setUsername(userUpdateModel.getUsername());
            }
            if (userUpdateModel.getPassword() != null && !userUpdateModel.getPassword().equals(existingUser.getPassword())) {
                existingUser.setPassword(userUpdateModel.getPassword());
            }
            return Mapper.mapUserEntityToUserModel(userRepository.save(existingUser));
        }
        String message = String.format("User with email %s not found", userUpdateModel.getEmail());
        log.info(message);
        throw new NoSuchElementException(message);
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
        } else {
            String message = String.format("User with id %d not found", id);
            log.info(message);
            throw new NoSuchElementException(message);
        }
    }

    @Override
    public UserModel login(UserLoginModel userLoginModel) {
        if (userLoginModel.getEmail().isEmpty() || userLoginModel.getEmail().isBlank()
            || userLoginModel.getPassword().isEmpty() || userLoginModel.getPassword().isBlank()) {
            String message = "Invalid input, please try again";
            log.info(message);
            throw new InputMismatchException(message);
        }
        Optional<User> userToLogin = userRepository.findByEmail(userLoginModel.getEmail());
        if (userToLogin.isPresent()) {
            if (userLoginModel.getPassword().equals(userToLogin.get().getPassword())) {
                return Mapper.mapUserEntityToUserModel(userToLogin.get());
            } else {
                String message = "Invalid input, please try again";
                log.info(message);
                throw new InvalidInputException(message);
            }
        } else {
            String message = "User not found";
            log.info(message);
            throw new NoSuchElementException(message);
        }
    }
}
