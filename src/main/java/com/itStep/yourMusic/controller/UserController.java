package com.itStep.yourMusic.controller;

import com.itStep.yourMusic.domain.Role;
import com.itStep.yourMusic.domain.User;
import com.itStep.yourMusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {


    @Autowired
    private UserService userService;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "userList";
    }

    @GetMapping("/{user}/Edit")
    public String editUser(
            @PathVariable User user,
            Model model

    ) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping("/{user}/Save")
    public String userSave(
            @RequestParam("userId") User user,
            @RequestParam Map<String, String> form
    ) {

        userService.saveUsersRoles(user,form);
        return ("redirect:/user");
    }
    @PostMapping("/{user}/Delete")
    public String userDelete(
            @PathVariable User user

    ) {

        userService.deleteUser(user);
        return ("redirect:/user");
    }
}
