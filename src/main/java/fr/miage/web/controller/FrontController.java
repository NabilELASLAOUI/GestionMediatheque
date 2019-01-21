package fr.miage.web.controller;

import fr.miage.core.entity.User;
import fr.miage.core.entity.UserMedia;
import fr.miage.core.service.MediaService;
import fr.miage.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/*le controlleur de la page d'accueil du site  */
@Controller
public class FrontController {
    private static final Logger LOGGER = LoggerFactory.getLogger(User.class);

    @Autowired
    private UserService userService;

    @Autowired
    MediaService mediaService;

    /*  cette methode  affiche la page d'accueil avec la liste des medias disponible  */
    @GetMapping("/")
    public String index(Model model){
       String content="index";
       String title="Accueil";
       model.addAttribute("title", title);
       model.addAttribute("content", content);
       model.addAttribute("AccueilSubscription","accueil");
       model.addAttribute("medias", this.mediaService.findAll());
        return "base";
    }

    /*  cette methode  renvoie le formulaire dd'authentification  */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Model model, Principal principal) {
        return "login";
    }

    /*  cette methode  renvoie les emprunts de tous les users */
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYE')")
    @RequestMapping(value = "/borrowing",method = RequestMethod.GET)
    public String getAll(Model model) {
        List<Set<UserMedia>> emprunts= new ArrayList<>();
        List<User> users = userService.findAll();
        for (User user : users)
        {
            emprunts.add(user.getUserMedias());
        }

        model.addAttribute("emprunts", emprunts);
        /*************   Title and Content html*******************************/
        model.addAttribute("title", "Emprunt");
        model.addAttribute("content", "media/emprunt");
        model.addAttribute("urlBorrowing","Emprunt");

        return "base";
    }

}
