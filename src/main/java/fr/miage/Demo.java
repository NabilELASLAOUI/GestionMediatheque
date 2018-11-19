package fr.miage;

import fr.miage.core.entity.Customer;
import fr.miage.core.entity.Subscription;
import fr.miage.core.service.CustomerService;
import fr.miage.core.service.SubscriptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
public class Demo implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(Demo.class);

    @Autowired
    CustomerService customerService;
    @Autowired
    SubscriptionService subscriptionService;

    @Override
    @Transactional
    public void run(String... arg0) throws Exception {

        // Customers
        Customer c1 = customerService.findByName("Fnac");
        if (c1 == null) {
            c1 = new Customer("Fnac");
            customerService.save(c1);
            LOGGER.info("Fnac created");
            LOGGER.debug("Fnac created profile dev");
        }
        Customer c2 = customerService.findByName("DECATHLON");
        if (c2 == null) {
            c2 = new Customer("DECATHLON");
            customerService.save(c2);
            LOGGER.info("DECATHLON created");
            LOGGER.debug("DECATHLON created profile dev");
        }

        // Subscription
            Subscription s1 = new Subscription(new Date());
            subscriptionService.save(s1);
            LOGGER.info("Subscription 1 created");
            LOGGER.debug("Subscription 1 created profile dev");

            Subscription s2 = new Subscription( new Date());
            subscriptionService.save(s2);
            LOGGER.info("Subscription 2 created");
            LOGGER.debug("Subscription 2 created profile dev");

    }
}