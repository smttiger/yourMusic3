package com.itStep.yourMusic.controller;

import com.itStep.yourMusic.domain.Role;
import com.itStep.yourMusic.domain.User;
import com.itStep.yourMusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAllUsers());
        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{user}/Edit")
    public String editUser(
            @PathVariable User user,
            Model model

    ) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{user}/Save")
    public String userSave(
            @RequestParam("userId") User user,
            @RequestParam Map<String, String> form
    ) {

        userService.saveUsersRoles(user, form);
        return ("redirect:/user");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/{user}/Delete")
    public String userDelete(
            @PathVariable User user

    ) {
        userService.deleteUser(user);
        return ("redirect:/user");
    }

    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user){
        model.addAttribute("username",user.getUsername());
        return "profile";
    }

    @PostMapping("profile/savePassword")
    public String updatePassword(
            @AuthenticationPrincipal User user,
            @RequestParam String password,

            Model model
    ){
        userService.updatePassword(user, password,  model);
        return "profile";
    }
    @PostMapping("profile/saveEmail")
    public String updateEmail(
            @AuthenticationPrincipal User user,
            @RequestParam String email,

            Model model
    ){
        userService.updateEmail(user, email,  model);
        return "profile";
    }

}
