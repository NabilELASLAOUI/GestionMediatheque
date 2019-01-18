package fr.miage;

import fr.miage.core.entity.User;
import fr.miage.core.service.EmailUtil;
import fr.miage.core.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.internet.AddressException;

@Component
public class Demo implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Demo.class);

    @Autowired
    SubscriptionService subscriptionService;

/*    @Autowired
    private EmailUtil emailUtil;
*/
    @Override
    @Transactional
    public void run(String... arg0) throws Exception {
/*
            SimpleMailMessage message = emailUtil.composeEmail();
            mailSender.send(message);
*/


        }
}
