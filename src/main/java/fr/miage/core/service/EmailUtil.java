package fr.miage.core.service;

import fr.miage.core.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

/**
 * @author sudarsan
 */
@Component
public class EmailUtil {

    @Autowired
    private Environment env;

    public SimpleMailMessage composeEmail() {

        final SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("allo");
        message.setText("Bienvenue Chez nous");
        message.setTo("adilazirar@gmail.com".toString());
        //message.setTo(user.getUserMail());
        message.setFrom("miagemulhousetest@gmail.com".toString());
        return message;
    }

}