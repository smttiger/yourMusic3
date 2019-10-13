package com.itStep.yourMusic.controller;

import com.itStep.yourMusic.domain.Role;
import com.itStep.yourMusic.domain.User;
import com.itStep.yourMusic.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userRepo.findAll());
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
        Set<String> roles = Arrays.stream(Role.values()).
                map(Role::name).collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepo.save(user);
        return ("redirect:/user");
    }
    @PostMapping("/{user}/Delete")
    public String userDelete(
            @PathVariable User user

    ) {
        userRepo.delete(user);
        return ("redirect:/user");
    }
}
