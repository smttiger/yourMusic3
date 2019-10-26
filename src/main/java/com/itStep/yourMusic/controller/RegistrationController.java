package com.itStep.yourMusic.controller;

import com.itStep.yourMusic.domain.User;
import com.itStep.yourMusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {


    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {

        User userFromDb = userService.findUser(user);
        if (userFromDb != null) {
            model.addAttribute("message", "User with such username is already exists!");
            return "registration";
        }
        userService.saveNewUser(user,model);
        return ("mailReport");
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);
        if (isActivated) {
            model.addAttribute("message", "Your account was successfully activated!");
        } else {
            model.addAttribute("message", "Activation code is not found");
        }
        return "login";
    }
}
