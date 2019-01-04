package fr.miage.web.controller;


import fr.miage.core.entity.User;
import fr.miage.core.entity.VerificationToken;
import fr.miage.core.service.RoleService;
import fr.miage.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    ApplicationEventPublisher eventPublisher;


    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute("User", new User());
        return "login";
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost() {
        LOGGER.info("--------------> Login");
        return "redirect:/user";
    }
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logout() {
        return "redirect:/";
    }
    @PreAuthorize("hasAnyRole('Admin','Employe')")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        /***********  List des users   *****************/
        model.addAttribute("users", this.userService.findAll());
        model.addAttribute("User", new User());
        /*************   Title and Content html*******************************/
        model.addAttribute("title", "Utilisateurs");
        model.addAttribute("content", "user/index");
        model.addAttribute("urlUser","utilisateurs");
        return "base";
    }

    @PreAuthorize("hasAnyRole('Admin','Employe')")
    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addUser(Model model) {
        /*************   add a media*******************************/
        model.addAttribute("action","/user/create");
        model.addAttribute("User", new User());
        model.addAttribute("roles", roleService.findAll());
        /*************   Title and Content html*******************************/
        model.addAttribute("title", "Medias");
        model.addAttribute("content", "user/add");
        model.addAttribute("urlUSer","user");
        return "base";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String submitCreate(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model, WebRequest request) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("action","/user/create");
            model.addAttribute("User", user);
            model.addAttribute("title", "Utilisateurs");
            model.addAttribute("content", "user/index");
            return "base";
        }

        User  registered  = userService.save(user);
        if (registered == null) {
            bindingResult.rejectValue("email", "message.regError");
        }
        try {
            String appUrl = request.getContextPath();
            eventPublisher.publishEvent(new OnRegistrationCompleteEvent(registered, request.getLocale(), appUrl));
        } catch (Exception me) {
            model.addAttribute("action","/user/create");
            model.addAttribute("User", user);
            model.addAttribute("title", "Utilisateurs");
            model.addAttribute("content", "user/index");
            return "base";
        }
        return "redirect:/user";
    }

    @Transactional
    @RequestMapping(value = "/regitrationConfirm", method = RequestMethod.GET)
    public String confirmRegistration(WebRequest request, Model model, @RequestParam("token") String token) {

        VerificationToken verificationToken = userService.getVerificationToken(token);
        User user = verificationToken.getUser();
        userService.save(user);
        return "redirect:/user";
    }

    // Recherche par nom
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String search(@RequestParam("userName") String userName, Model model) {
        /***********  List des users   *****************/
        model.addAttribute("users", this.userService.findByUserName(userName));
        /***********  Ajout d'un user ****************/
        model.addAttribute("action","/user/create");
        model.addAttribute("User", new User());
        model.addAttribute("roles", roleService.findAll());
        /*************   Title and Content html*******************************/
        model.addAttribute("title", "Utilisateurs");
        model.addAttribute("content", "user/index");
        model.addAttribute("urlUser","utilisateurs");
        return "base";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") Long id, Model model) {
        model.addAttribute("User", userService.findByuserId(id));
        model.addAttribute("roles", roleService.findAll());
        String action="/user/create";
        model.addAttribute("action",action);
        /*************   Title and Content html*******************************/
        String title="Modification";
        model.addAttribute("title", title);
        String content="user/index";
        model.addAttribute("content", content);
        return "base";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/user";
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public String detail(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.findByuserId(id));
        model.addAttribute("roles", roleService.findAll());
        String title="Detail";
        model.addAttribute("title", title);
        String content="user/detail";
        model.addAttribute("content", content);
        return "base";
    }
}

