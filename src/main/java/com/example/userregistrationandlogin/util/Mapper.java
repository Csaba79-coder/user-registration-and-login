package com.example.userregistrationandlogin.util;

import com.example.userregistrationandlogin.entity.User;
import com.example.userregistrationandlogin.model.UserModel;
import com.example.userregistrationandlogin.model.UserRegisterModel;
import com.example.userregistrationandlogin.model.UserUpdateModel;

public class Mapper {

    public static UserModel mapUserRegisterModelToUserModel(UserRegisterModel userRegModel) {
        User user = new User();
        user.setEmail(userRegModel.getEmail());
        user.setUsername(userRegModel.getUsername());
        user.setPassword(userRegModel.getPassword());
        return mapUserEntityToUserModel(user);
    }

    public static UserModel mapUserEntityToUserModel(User user) {
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setEmail(user.getEmail());
        userModel.setUsername(user.getUsername());
        return userModel;
    }

    public static User mapUserRegistrationModelToUserEntity(UserRegisterModel userRegModel) {
        User user = new User();
        user.setEmail(userRegModel.getEmail());
        user.setUsername(userRegModel.getUsername());
        user.setPassword(userRegModel.getPassword());
        return user;
    }

    public static User mapUserUpdateModelToUserEntity(UserUpdateModel userUpdateModel) {
        User user = new User();
        user.setEmail(userUpdateModel.getEmail());
        user.setUsername(userUpdateModel.getUsername());
        return user;
    }

    private Mapper() {
    }
}
