package com.itStep.yourMusic.controller;

import com.itStep.yourMusic.domain.Role;
import com.itStep.yourMusic.domain.User;
import com.itStep.yourMusic.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users",userRepo.findAll());
        return "userList";
    }
@GetMapping("/{user}/Edit")
    public String editUser(
            @PathVariable User user,
            Model model

){
        model.addAttribute("user",user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
}
}
