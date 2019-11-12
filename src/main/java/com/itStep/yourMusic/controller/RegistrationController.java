package com.itStep.yourMusic.controller;

import com.itStep.yourMusic.domain.User;
import com.itStep.yourMusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

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
    public String addUser(
            @RequestParam String password2,
            @Valid User user,
            BindingResult bindingResult,
            Model model) {
        if (userService.checkUser(user,bindingResult,model,password2)){
        userService.saveNewUser(user, model);
       return ("mailReport");}
       else{
            return "registration";
        }
    }

    @GetMapping("/activate/{code}")
    public String activate(Model model, @PathVariable String code) {
        boolean isActivated = userService.activateUser(code);
        if (isActivated) {
            model.addAttribute("message", "Your account was successfully activated!");
            model.addAttribute("alert","alert-success");
        } else {
            model.addAttribute("message", "Activation code is not found");
            model.addAttribute("alert","alert-danger");
        }
        return "login";
    }
}
