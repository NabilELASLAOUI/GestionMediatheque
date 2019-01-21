package fr.miage.web.controller;

import fr.miage.core.entity.User;
import fr.miage.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.MessageSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent> {

    /* l'injection de service */
    @Autowired
    private UserService service;

    /* l'injection de messages */
    @Autowired
    private MessageSource messages;

    /* l'injection de JavaMailSender */
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void onApplicationEvent(OnRegistrationCompleteEvent event) {
        this.confirmRegistration(event);
    }

    /*cette methode envoie un mail de confirmation d'inscription ainsi elle contient objet, url de confirmation et le message a envoyer au client*/
    private void confirmRegistration(OnRegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        service.createVerificationToken(user, token);

        String recipientAddress = user.getUserMail();
        String subject = "Confirmation d'inscription";
        String confirmationUrl = event.getAppUrl() + "/user/regitrationConfirm.html?token=" + token;
        String message = messages.getMessage("merci de cliquer sur le lien pour activer votre compte: ", null, event.getLocale());

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(recipientAddress);
        email.setSubject(subject);
        email.setText(message + " " + "http://localhost:8080" + confirmationUrl);
        mailSender.send(email);
    }
}