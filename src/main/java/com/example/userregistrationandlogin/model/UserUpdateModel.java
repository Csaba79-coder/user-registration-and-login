package com.example.userregistrationandlogin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateModel {

    private Long id;
    private String email;
    private String username;
    private String password;
    private String repeatPassword;
}
