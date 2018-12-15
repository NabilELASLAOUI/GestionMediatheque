package fr.miage.web.controller;

import fr.miage.core.entity.Subscription;
import fr.miage.core.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/subscription")
public class SubscriptionController {

    @Autowired
    SubscriptionService subscriptionService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        /***********  List des inscription   *****************/
        final List<Subscription> subscriptions = this.subscriptionService.findAll();
        model.addAttribute("subscriptions", subscriptions);
        String content="subscription/index";
        /***********  Ajout d'une inscription ****************/
        String action="/subscription/create";
        model.addAttribute("action",action);
        model.addAttribute("Subscription", new Subscription());
        /*************   Title and Content html*******************************/
        String title="Inscription";
        model.addAttribute("title", title);
        model.addAttribute("content", content);
        return "base";
    }
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String submitCreate(@Valid @ModelAttribute Subscription subscription, BindingResult bindingResult, Model model) {
        LOGGER.info("******* create subscription *******");
        if (bindingResult.hasErrors()) {
            /***********  errors subscription create ****************/
            LOGGER.info("-----------> errors subscription create");
            String action="/subscription/create";
            model.addAttribute("action",action);
            model.addAttribute("Subscription", subscription);
            /*************   Title and Content html*******************************/
            String title="Inscription";
            model.addAttribute("title", title);
            String content="subscription/index";
            model.addAttribute("content", content);
            return "base";
        }
        subscriptionService.save(subscription);
        return "redirect:/subscription";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") Long id, Model model) {
        model.addAttribute("subscription", this.subscriptionService.findById(id));
        return "subscription/form";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String submitEdit(@Valid @ModelAttribute Subscription subscription, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "subscription/form";
        }

        subscriptionService.save(subscription);
        return "redirect:/subscription";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        subscriptionService.delete(id);
        return "redirect:/subscription";
    }
}
