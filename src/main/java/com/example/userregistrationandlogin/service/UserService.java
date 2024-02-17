package com.example.userregistrationandlogin.service;

import com.example.userregistrationandlogin.entity.User;
import com.example.userregistrationandlogin.model.UserLoginModel;
import com.example.userregistrationandlogin.model.UserModel;
import com.example.userregistrationandlogin.model.UserRegisterModel;
import com.example.userregistrationandlogin.model.UserUpdateModel;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserModel> getAllUsers();
    UserModel retrieveUserById(Long id);
    UserModel retrieveUserByEmail(String email);
    UserModel createUser(UserRegisterModel userRegModel);
    UserModel updateUser(UserUpdateModel userUpdateModel);
    void deleteUser(Long id);
    UserModel login(UserLoginModel userLoginModel);
}
