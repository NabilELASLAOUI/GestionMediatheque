package fr.miage.web.controller;


import fr.miage.core.entity.Media;
import fr.miage.core.entity.User;
import fr.miage.core.service.MediaTypeService;
import fr.miage.core.service.MediaService;
import fr.miage.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.Optional;


@Controller
@RequestMapping("/media")
public class MediaController {

    @Autowired
    MediaService mediaService;

    @Autowired
    MediaTypeService mediaTypeService;

    @Autowired
    private UserService userService;

    //@PreAuthorize("hasAnyRole('ADMIN')")

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        /***********  List des medias   *****************/
        model.addAttribute("medias", this.mediaService.findAll());
        model.addAttribute("Media", new Media());
        /*************   Title and Content html*******************************/
        model.addAttribute("title", "Medias");
        model.addAttribute("content", "media/index");
        model.addAttribute("urlMedia","Medias");
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        if (currentUser != "anonymousUser"){
            Optional<User> user = userService.findByUserName(currentUser);
            model.addAttribute("username", user.get().getUserName());
            model.addAttribute("userID", user.get().getUserId());
        }
        return "base";
    }

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addMedia(Model model) {
        /*************   add a media*******************************/
        model.addAttribute("action","/media/create");
        model.addAttribute("mediaTypes", mediaTypeService.findAll());
        model.addAttribute("media", new Media());
        /*************   Title and Content html*******************************/
        model.addAttribute("title", "Medias");
        model.addAttribute("content", "media/add");
        model.addAttribute("urlMedia","Media");
        return "base";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String submitCreate(@Valid @ModelAttribute Media media, BindingResult bindingResult, Model model, WebRequest request) {
        if (bindingResult.hasErrors()) {
            System.out.println("tttttttttttttttttttttttttttttttttttttttttttttttttttttt");
            System.out.println(bindingResult.getModel().values());
            model.addAttribute("action","/media/create");
            model.addAttribute("mediaTypes", mediaTypeService.findAll());
            model.addAttribute("title", "Medias");
            model.addAttribute("content", "media/add");
            model.addAttribute("urlMedia","Media");
            return "base";
        }

        mediaService.save(media);

        return "redirect:/media";
    }


    // Recherche par nom
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam("mediaTitle") String mediaName, Model model) {
        /***********  List des medias   *****************/
        model.addAttribute("medias", this.mediaService.findByMediaTitle(mediaName));
        /***********  Ajout d'un media ****************/
        model.addAttribute("action","/media/create");
        model.addAttribute("Media", new Media());
        model.addAttribute("mediaTypes", mediaTypeService.findAll());
        /*************   Title and Content html*******************************/
        model.addAttribute("title", "Medias");
        model.addAttribute("content", "media/index");
        model.addAttribute("urlMedia","medias");
        return "base";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") Long id, Model model) {
        model.addAttribute("media", mediaService.findByMediaId(id));
        model.addAttribute("mediaTypes", mediaTypeService.findAll());
        String action="/media/create";
        model.addAttribute("action",action);
        /*************   Title and Content html*******************************/
        String title="Modification";
        model.addAttribute("title", title);
        String content="media/add";
        model.addAttribute("content", content);
        return "base";
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(@RequestParam("id") Long id, Model model) {
        model.addAttribute("media", mediaService.findByMediaId(id));
        model.addAttribute("mediaTypes", mediaTypeService.findAll());
        String title="Detail";
        model.addAttribute("title", title);
        String content="media/detail";
        model.addAttribute("content", content);
        return "base";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        mediaService.delete(id);
        return "redirect:/media";
    }
}

