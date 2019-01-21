package fr.miage.web.controller;

import fr.miage.core.entity.Subscription;
import fr.miage.core.entity.User;
import fr.miage.core.service.SubscriptionService;
import fr.miage.core.service.SubscriptionTypeService;
import fr.miage.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.Optional;


@Controller
@RequestMapping("/subscription")
public class SubscriptionController {

    /* l'injection de subscriptionService */
    @Autowired
    SubscriptionService subscriptionService;

    /* l'injection de subscriptionTypeService */
    @Autowired
    SubscriptionTypeService subscriptionTypeService;

    /* l'injection de userService */
    @Autowired
    private UserService userService;

    /* l'injection de  JavaMailSender */
    @Autowired
    private JavaMailSender emailSender;

    private static final Logger LOGGER = LoggerFactory.getLogger(User.class);

    /* cette methode renvoie la liste des abonnements */
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYE','CLIENT')")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        /***********  List des abonnements   *****************/
        model.addAttribute("subscriptions", this.subscriptionService.findAll());
        /***********  Ajout d'un abonnement ****************/
        model.addAttribute("action","/subscription/create");
        model.addAttribute("subscriptionTypes", subscriptionTypeService.findAll());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("Subscription", new Subscription());
        /*************   Title and Content html*******************************/
        model.addAttribute("title", "Abonnement");
        model.addAttribute("content", "subscription/index");
        model.addAttribute("urlSubscription","subscriprion");

        return "base";
    }

    /* Cette methode sert a la creation d'un abonnement */
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYE','CLIENT')")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String submitCreate(@Valid @ModelAttribute Subscription subscription, BindingResult bindingResult, Model model) {
        //LOGGER.info("******* create subscription *******");
        if (bindingResult.hasErrors()) {
            /***********  errors subscription create ****************/
            LOGGER.info("-----------> errors subscription create");
            model.addAttribute("action","/subscription/create");
            model.addAttribute("subscriptionTypes", subscriptionTypeService.findAll());
            model.addAttribute("Subscription", subscription);
            /*************   Title and Content html*******************************/
            model.addAttribute("title", "Abonnement");
            model.addAttribute("content", "subscription/index");
            return "base";
        }
        subscriptionService.save(subscription);
        return "redirect:/subscription";
    }

    /* Cette methode sert a la récuperation des données pour les modifier */
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYE','CLIENT')")
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") Long id, Model model) {
        model.addAttribute("Subscription", this.subscriptionService.findBySubscriptionId(id));
        model.addAttribute("subscriptionTypes", subscriptionTypeService.findAll());
        model.addAttribute("user", subscriptionService.findBySubscriptionId(id).getUser_sub().getUserId());
        String action="/subscription/edit";
        model.addAttribute("action",action);
        /*************   Title and Content html*******************************/
        String title="Modification";
        model.addAttribute("title", title);
        String content="subscription/index";
        model.addAttribute("content", content);
        return "base";
    }
    /*cette methode sert a la modification d'un abonnement*/
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYE','CLIENT')")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String editPost(@Valid @ModelAttribute Subscription subscription, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("Subscription", subscription);
            model.addAttribute("subscriptionTypes", subscriptionTypeService.findAll());
            model.addAttribute("user", subscription.getUser_sub().getUserId());
            String action="/subscription/edit";
            model.addAttribute("action",action);
            /*************   Title and Content html*******************************/
            String title="Modification";
            model.addAttribute("title", title);
            String content="subscription/index";
            model.addAttribute("content", content);
            return "base";
        }

        /* ce code permet d'envoyé un mail au client pour lui informer de la validation de son abonnement par l'administratuer */
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(subscription.getUser_sub().getUserMail());
        message.setSubject("Validation de votre abonnement ");
        message.setText("Nous sommes très heureux de confirmer votre abonnement . \n" +
                "Toute l’équipe de Miage vous remercie pour votre confiance et vous souhaite la bienvenue.");
        subscriptionService.save(subscription);
        emailSender.send(message);
        return "redirect:/subscription";
    }
    /*Cette methode sert a supprimer un abonnement*/
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYE','CLIENT')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        subscriptionService.delete(id);
        return "redirect:/subscription";
    }

    /* cette methode renvoie les details d'un abonnement*/
    @PreAuthorize("hasAnyRole('Admin','Employe','CLIENT')")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(@RequestParam("id") Long id, Model model) {
        model.addAttribute("subscription", subscriptionService.findBySubscriptionId(id));
        model.addAttribute("subscriptionTypes", subscriptionTypeService.findAll());
        model.addAttribute("users", userService.findAll());
        String title="Detail";
        model.addAttribute("title", title);
        String content="subscription/detail";
        model.addAttribute("content", content);
        return "base";
    }

}
