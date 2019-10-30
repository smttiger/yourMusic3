package com.itStep.yourMusic.service;

import com.itStep.yourMusic.domain.Role;
import com.itStep.yourMusic.domain.User;
import com.itStep.yourMusic.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userRepo.findByUsername(username);
        if (user==null){
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public User findUser(User user) {
        return userRepo.findByUsername(user.getUsername());
    }

    public void saveNewUser(User user, Model model) {
        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        sendMessage(user, model);
    }

    private void sendMessage(User user, Model model) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello, %s \n" +
                            "Welcome to Your Music! Please follow next link to activate your account:\n" +
                            "http://localhost:8080/activate/%s",
                    user.getUsername(),
                    user.getActivationCode()
            );
            try {
                mailSenderService.send(user.getEmail(), "Activation code", message);
                model.addAttribute("mailReport", "Check your email and follow activation link");
                model.addAttribute("alert", "alert-success");
                userRepo.save(user);
            } catch (Exception e) {
                model.addAttribute("mailReport", "Enter existing email address and retry");
                model.addAttribute("alert", "alert-danger");
            }
        }
    }

    public void saveUsersRoles(User user, Map<String, String> form
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
    }

    public List<User> findAllUsers() {
        return userRepo.findAll();
    }

    public void deleteUser(User user) {
        userRepo.delete(user);
    }

    public boolean activateUser(String code) {
        User user = userRepo.findByActivationCode(code);
        if (user == null) {
            return false;
        }
        user.setActivationCode(null);
        user.setActive(true);
        userRepo.save(user);
        return true;
    }

    public void updatePassword(User user, String password, Model model) {

        if (!StringUtils.isEmpty(password)) {
            user.setPassword(passwordEncoder.encode(password));
            userRepo.save(user);
            model.addAttribute("passwordReport","Password was successfully changed");
        } else {
            model.addAttribute("passwordError", "Password can not be empty");
        }
        model.addAttribute("username", user.getUsername());
    }

    public boolean checkUser(User user, BindingResult bindingResult, Model model, String passwordConfirm) {
        boolean isConfirmEmpty = StringUtils.isEmpty(passwordConfirm);
        boolean differentPasswords = false;
        if (isConfirmEmpty) {
            model.addAttribute("password2Error", "Password confirmation can not be empty");
        }
        if (user.getPassword() != null && !user.getPassword().equals(passwordConfirm)) {
            model.addAttribute("passwordError", "Passwords are different!");
            model.addAttribute("passwordConf", passwordConfirm);
            differentPasswords = true;
        }
        User userFromDb = findUser(user);
        if (userFromDb != null) {
            model.addAttribute("usernameError", "User with such username is already exists!");
        }
        if (isConfirmEmpty || bindingResult.hasErrors() || (userFromDb != null) || differentPasswords) {
            Map<String, String> errors = ErrorService.getErrors(bindingResult);
            model.mergeAttributes(errors);
            model.addAttribute("user", user);
            return false;
        } else {
            return true;
        }

    }

    public void updateEmail(User user, String email, Model model) {
        if (!email.contains("@")) {
            model.addAttribute("emailError", "Email is not correct");
            model.addAttribute("email", email);
        }
        if (StringUtils.isEmpty(email)) {
            model.addAttribute("emailError", "Email can not be empty");
        }
        if (!StringUtils.isEmpty(email) && email.contains("@")) {
            String userEmail = user.getEmail();
            boolean isEmailChanged = (email != null && !email.equals(userEmail));// ||(userEmail!=null && !userEmail.equals(email));
            if (isEmailChanged) {
                user.setEmail(email);
                user.setActive(false);
                user.setActivationCode(UUID.randomUUID().toString());
                sendMessage(user, model);
            }
        }
        model.addAttribute("username", user.getUsername());
    }
}