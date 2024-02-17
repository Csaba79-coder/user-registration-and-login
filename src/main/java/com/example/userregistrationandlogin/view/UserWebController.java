package com.example.userregistrationandlogin.view;

import com.example.userregistrationandlogin.model.UserRegisterModel;
import com.example.userregistrationandlogin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class UserWebController {

    private final UserService userService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new UserRegisterModel());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserRegisterModel userRegModel) {
        userService.createUser(userRegModel);
        return "redirect:/list-users";
    }

    @GetMapping("/list-users")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "list-users";
    }
}
