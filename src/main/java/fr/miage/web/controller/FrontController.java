package fr.miage.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.security.Principal;

@Controller
public class FrontController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping("/")
    public String index(Model model){
       String content="index";
       String title="Accueil";
       model.addAttribute("title", title);
       model.addAttribute("content", content);
       model.addAttribute("AccueilSubscription","accueil");
       final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        if (currentUser != "anonymousUser"){
            model.addAttribute("username", currentUser);
        }
        return "base";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Model model, Principal principal) {
        return "login";
    }

}
