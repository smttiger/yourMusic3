package com.itStep.yourMusic.service;

import com.itStep.yourMusic.domain.Role;
import com.itStep.yourMusic.domain.User;
import com.itStep.yourMusic.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private MailSenderService mailSenderService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public User findUser(User user) {
        return userRepo.findByUsername(user.getUsername());
    }

    public void saveNewUser(User user) {
        user.setActive(false);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        if (!StringUtils.isEmpty(user.getEmail())){
            String message=String.format(
              "Hello, %s \n"+
              "Welcome to Your Music! Please follow next link to activate your account:\n"+
              "http://localhost:8080/activate/%s",
              user.getUsername(),
              user.getActivationCode()
            );
            mailSenderService.send(user.getEmail(),"Activation code",message);
        }
        userRepo.save(user);
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

    public List<User> findAllUsers(){
        return userRepo.findAll();
    }

    public void deleteUser(User user){
        userRepo.delete(user);
    }

    public boolean activateUser(String code) {
        User user=userRepo.findByActivationCode(code);
        if (user==null){
            return false;
        }
        user.setActivationCode(null);
        user.setActive(true);
        userRepo.save(user);
        return true;
    }
}
