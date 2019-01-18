package fr.miage.web.controller;


import fr.miage.core.entity.Role;
import fr.miage.core.entity.User;
import fr.miage.core.entity.VerificationToken;
import fr.miage.core.repository.UserRepository;
import fr.miage.core.service.RoleService;
import fr.miage.core.service.SubscriptionService;
import fr.miage.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;


import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    PasswordEncoder passwordEncoder;

    private static final Logger LOGGER = LoggerFactory.getLogger(User.class);


    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYE')")
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

    @RequestMapping(value = "/add",method = RequestMethod.GET)
    public String addUser(Model model) {
        /*************   add a user*******************************/
        model.addAttribute("action","/user/create");
        model.addAttribute("User", new User());
        List<Role> roles = new ArrayList<>();
        for (Role role : roleService.findAll()){
            if (!role.getRoleName().equalsIgnoreCase("ADMIN")){
                roles.add(role);
            }
        }
        model.addAttribute("roles", roles);
        /*************   Title and Content html*******************************/
        model.addAttribute("title", "Utilisateurs");
        model.addAttribute("content", "user/add");
        model.addAttribute("urlUSer","user");
        return "base";
    }
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String submitCreate(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getModel().values());
            model.addAttribute("action","/user/create");
            model.addAttribute("roles", roleService.findAll());
            model.addAttribute("title", "Utilisateurs");
            model.addAttribute("content", "user/add");
            model.addAttribute("urlUser","User");
            return "base";
        }
        LOGGER.info("--------->user role: "+user.getRoles().getRoleName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        LOGGER.info("save: "+userService.save(user));
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
    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYE')")
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

    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYE')")
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") Long id, Model model) {
        model.addAttribute("User", userService.findByuserId(id));
        model.addAttribute("roles", roleService.findAll());
        String action="/user/create";
        model.addAttribute("action",action);
        /*************   Title and Content html*******************************/
        String title="Modification";
        model.addAttribute("title", title);
        String content="user/add";
        model.addAttribute("content", content);
        return "base";
    }

    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYE')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/user";
    }

    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYE')")
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

    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYE','CLIENT')")
    @RequestMapping(value = "/monCompte", method = RequestMethod.GET)
    public String monCompte(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", userService.findByuserId(id));
        model.addAttribute("subscriptions", subscriptionService.findAll());
        model.addAttribute("userId", id);
        model.addAttribute("User", new User());
        String title="Mon Compte";
        model.addAttribute("title", title);
        String content="user/monCompte";
        model.addAttribute("content", content);
        return "base";
    }

    @PreAuthorize("hasAnyRole('Admin','Employe')")
    @RequestMapping(value = "/subscription", method = RequestMethod.POST)
    public String Usersubscription(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("action","/user/subscription");
            model.addAttribute("roles", roleService.findAll());
            model.addAttribute("title", "Utilisateurs");
            model.addAttribute("content", "user/monCompte");
            model.addAttribute("urlUser","User");
            return "base";
        }
        LOGGER.info("---------> user subscription");
        userService.save(user);
        return "redirect:/user";
    }
}