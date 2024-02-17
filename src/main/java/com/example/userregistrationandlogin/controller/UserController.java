package com.example.userregistrationandlogin.controller;

import com.example.userregistrationandlogin.model.UserLoginModel;
import com.example.userregistrationandlogin.model.UserModel;
import com.example.userregistrationandlogin.model.UserRegisterModel;
import com.example.userregistrationandlogin.model.UserUpdateModel;
import com.example.userregistrationandlogin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> renderAllUsers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/users")
    public ResponseEntity<UserModel> createUser(@RequestBody UserRegisterModel userRegModel) {
        return ResponseEntity.status(201).body(userService.createUser(userRegModel));
    }

    @GetMapping("/users/id/{id}")
    public ResponseEntity<UserModel> renderUserById(@PathVariable Long id) {
        return ResponseEntity.status(200).body(userService.retrieveUserById(id));
    }

    @GetMapping("/users/email/{email}")
    public ResponseEntity<UserModel> renderUserByEmail(@PathVariable String email) {
        return ResponseEntity.status(200).body(userService.retrieveUserByEmail(email));
    }

    @PatchMapping("/users")
    public ResponseEntity<UserModel> updateUser(@RequestBody UserUpdateModel updateModel) {
        return ResponseEntity.status(200).body(userService.updateUser(updateModel));
    }

    @DeleteMapping("/users/id/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.status(204).build();
    }

    @PostMapping("/login")
    public ResponseEntity<UserModel> login(@RequestBody UserLoginModel userLoginModel) {
        return ResponseEntity.status(200).body(userService.login(userLoginModel));
    }
}
