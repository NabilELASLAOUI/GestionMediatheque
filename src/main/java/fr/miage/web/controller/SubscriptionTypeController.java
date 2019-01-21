package fr.miage.web.controller;

import fr.miage.core.entity.MediaType;
import fr.miage.core.entity.SubscriptionType;
import fr.miage.core.service.MediaTypeService;
import fr.miage.core.service.SubscriptionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/subscriptiontype")
public class SubscriptionTypeController {


    /* l'injection de subscriptionTypeService */
    @Autowired
    SubscriptionTypeService subscriptionTypeService;

    /* cette methode renvoie la liste des types d'abonnement*/
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        /***********  List des types d'abonnement   *****************/
        /***********  Ajout d'un type d'abonnement ****************/
        model.addAttribute("action","/subscriptiontype/create");
        model.addAttribute("SubscriptionType", new SubscriptionType());
        model.addAttribute("subscriptiontypes", subscriptionTypeService.findAll());
        /*************   Title and Content html*******************************/
        model.addAttribute("title", "Subscription types");
        model.addAttribute("content", "subscriptiontype/index");
        model.addAttribute("urlSubscriptiontype","subscriptiontypes");
        return "base";
    }

    /*Cette methode sert a la creation d'un abonnement */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String submitCreate(@Valid @ModelAttribute SubscriptionType subscriptiontype, BindingResult bindingResult, Model model) {
        //LOGGER.info("******* create type abonnement *******");
        if (bindingResult.hasErrors()) {
            /***********  errors abonnement type ****************/
            model.addAttribute("action","/subscriptionType/create");
            model.addAttribute("SubscriptionType", subscriptiontype);
            /*************   Title and Content html*******************************/
            model.addAttribute("title", "Subscriptiontypes");
            model.addAttribute("content", "subscriptiontype/index");
            return "base";
        }
        subscriptionTypeService.save(subscriptiontype);
        return "redirect:/subscriptiontype";
    }

    /* Cette methode sert a la récuperation des données d'un type d'abonnement pour les modifier */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") Long id, Model model) {
        model.addAttribute("SubscriptionType", subscriptionTypeService.findByTypeId(id));
        model.addAttribute("subscriptiontypes", subscriptionTypeService.findAll());
        String action="/subscriptiontype/create";
        model.addAttribute("action",action);
        /*************   Title and Content html*******************************/
        String title="Modification";
        model.addAttribute("title", title);
        String content="subscriptiontype/index";
        model.addAttribute("content", content);
        return "base";
    }

    /*Cette methode supprime un type d'abonnement*/
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        subscriptionTypeService.delete(id);
        return "redirect:/subscriptiontype";
    }

}
