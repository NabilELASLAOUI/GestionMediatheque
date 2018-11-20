package fr.miage.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FrontController {
    @GetMapping("/")
    public String index(Model model){
       String content="index";
       String title="Accueil";
       model.addAttribute("title", title);
       model.addAttribute("content", content);

        return "base";
    }

}
