package com.itStep.yourMusic;

import com.itStep.yourMusic.service.MailSenderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailSenderServiceTest {
    @Autowired
    private MailSenderService mailSenderService;

    @Test(expected = Exception.class)
    public void sendFailTest(){
        mailSenderService.send("sss@fff","","");
    }

    @Test
    public void sendSuccessTest(){
        mailSenderService.send("sergeyfab@yandex.ru","","");
    }
}
