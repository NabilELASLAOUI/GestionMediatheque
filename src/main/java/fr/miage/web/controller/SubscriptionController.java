package fr.miage.web.controller;

import fr.miage.core.entity.Subscription;
import fr.miage.core.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/subscription")
public class SubscriptionController {

    @Autowired
    SubscriptionService subscriptionService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        final List<Subscription> subscriptions = this.subscriptionService.findAll();
        /***********  List des inscription   *****************/
        model.addAttribute("subscriptions", subscriptions);
        String content="subscription/index";
        /***********  Ajout d'une inscription ****************/
        String action="/subscription/add";
        model.addAttribute("action",action);
        model.addAttribute("Subscription", new Subscription());
        /*************   Title and Content html*******************************/
        String title="Inscription";
        model.addAttribute("title", title);
        model.addAttribute("content", content);
        return "base";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("Subscription", new Subscription());
        return "subscription/form";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String submitCreate(@Valid @ModelAttribute Subscription subscription, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "subscription/form";
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
