package edu.pl.pollub.listener;

import edu.pl.pollub.entity.User;
import edu.pl.pollub.event.OnRegistrationCompleteEvent;
import edu.pl.pollub.service.UserService;
import edu.pl.pollub.service.implementation.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by Dell on 2017-03-16.
 */
@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {
    @Autowired
    private UserService userService;
    @Autowired
    private MailServiceImpl mailService;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.createVerificationToken(user, token);

        String recipientAddress = user.getEmail();
        String subject = "Registration Confirmation";
        String confirmationUrl
                = event.getAppUrl() + "/registration/confirm?token=" + token;
        String message = "Please click this link to verify your account: ";


        mailService.sendMail("from@no-spam.com",recipientAddress,subject,message + "http://localhost:8081" + confirmationUrl);
    }
}
