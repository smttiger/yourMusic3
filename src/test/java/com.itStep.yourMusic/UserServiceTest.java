package com.itStep.yourMusic;

import com.itStep.yourMusic.domain.Role;
import com.itStep.yourMusic.domain.User;
import com.itStep.yourMusic.repository.UserRepo;
import com.itStep.yourMusic.service.MailSenderService;
import com.itStep.yourMusic.service.UserService;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.Model;

import java.util.Collections;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private MailSenderService mailSenderService;

    @MockBean
    private UserRepo userRepo;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @MockBean
    private Model model;


    @Test
    public void activateUserTest() {
        User user = new User();
        user.setActivationCode("some code");
        Mockito.doReturn(user)
                .when(userRepo)
                .findByActivationCode("another code");
        boolean isUserActivated = userService.activateUser("another code");
        Assert.assertTrue(isUserActivated);
        Assert.assertNull(user.getActivationCode());
        Mockito.verify(userRepo, Mockito.times(1)).save(user);
    }

    @Test
    public void activateUserNegativeTest() {
        boolean isUserActivated = userService.activateUser("bla bla bla");
        Assert.assertFalse(isUserActivated);
        Mockito.verify(userRepo, Mockito.times(0)).save(ArgumentMatchers.any(User.class));
    }

    @Test
    public void saveNewUserTest() {
        User user = new User();
        user.setEmail("some@mail.ru");
        userService.saveNewUser(user, model);
        Assert.assertNotNull(user.getActivationCode());
        Assert.assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));

        Mockito.verify(mailSenderService,Mockito.times(1)).send(
                ArgumentMatchers.eq(user.getEmail()),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString()
        );

        Mockito.verify(userRepo,Mockito.times(1)).save(user);
    }
}