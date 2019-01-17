package fr.miage.web.controller;

import fr.miage.core.entity.User;
import fr.miage.core.service.MediaService;
import fr.miage.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.security.Principal;
import java.util.Optional;

@Controller
public class FrontController {
    private static final Logger LOGGER = LoggerFactory.getLogger(User.class);

    @Autowired
    private UserService userService;

    @Autowired
    MediaService mediaService;

    @GetMapping("/")
    public String index(Model model){
       String content="index";
       String title="Accueil";
       model.addAttribute("title", title);
       model.addAttribute("content", content);
       model.addAttribute("AccueilSubscription","accueil");
       model.addAttribute("medias", this.mediaService.findAll());
       final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        if (currentUser != "anonymousUser"){
            Optional<User> user = userService.findByUserName(currentUser);
            model.addAttribute("username", user.get().getUserName());
            model.addAttribute("userID", user.get().getUserId());
        }
        return "base";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Model model, Principal principal) {
        return "login";
    }

}
