package fr.miage.web.controller;

import fr.miage.core.entity.Subscription;
import fr.miage.core.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @PreAuthorize("hasAnyRole('Admin','Employe','Client')")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        /***********  List des inscription   *****************/
        model.addAttribute("subscriptions", this.subscriptionService.findAll());
        /***********  Ajout d'une inscription ****************/
        model.addAttribute("action","/subscription/create");
        model.addAttribute("Subscription", new Subscription());
        /*************   Title and Content html*******************************/
        model.addAttribute("title", "Inscription");
        model.addAttribute("content", "subscription/index");
        model.addAttribute("urlSubscription","subscriprion");
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        if (currentUser != "anonymousUser"){
            model.addAttribute("username", currentUser);
        }
        return "base";
    }
    @PreAuthorize("hasAnyRole('Admin','Employe','Client')")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String submitCreate(@Valid @ModelAttribute Subscription subscription, BindingResult bindingResult, Model model) {
        LOGGER.info("******* create subscription *******");
        if (bindingResult.hasErrors()) {
            /***********  errors subscription create ****************/
            LOGGER.info("-----------> errors subscription create");
            model.addAttribute("action","/subscription/create");
            model.addAttribute("Subscription", subscription);
            /*************   Title and Content html*******************************/
            model.addAttribute("title", "Inscription");
            model.addAttribute("content", "subscription/index");
            return "base";
        }
        subscriptionService.save(subscription);
        return "redirect:/subscription";
    }

    @PreAuthorize("hasAnyRole('Admin','Employe','Client')")
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") Long id, Model model) {
        model.addAttribute("Subscription", this.subscriptionService.findBySubscriptionId(id));
        String action="/subscription/create";
        model.addAttribute("action",action);
        /*************   Title and Content html*******************************/
        String title="Modification";
        model.addAttribute("title", title);
        String content="subscription/index";
        model.addAttribute("content", content);
        return "base";
    }
    @PreAuthorize("hasAnyRole('Admin','Employe','Client')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        subscriptionService.delete(id);
        return "redirect:/subscription";
    }

    @PreAuthorize("hasAnyRole('Admin','Employe','Client')")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(@RequestParam("id") Long id, Model model) {
        model.addAttribute("subscription", subscriptionService.findBySubscriptionId(id));
        String title="Detail";
        model.addAttribute("title", title);
        String content="subscription/detail";
        model.addAttribute("content", content);
        return "base";
    }
}
