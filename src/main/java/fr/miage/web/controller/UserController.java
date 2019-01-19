package fr.miage.web.controller;


import fr.miage.core.entity.*;
import fr.miage.core.repository.UserMediaRepository;
import fr.miage.core.repository.UserRepository;
import fr.miage.core.service.MediaService;
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


import javax.persistence.EntityManager;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MediaService mediaService;

    @Autowired
    private UserMediaRepository userMediaRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private SubscriptionService subscriptionService;

    @Autowired
    ApplicationEventPublisher eventPublisher;

    @Autowired
    PasswordEncoder passwordEncoder;

    private EntityManager entityManager;

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

    @PreAuthorize("hasAnyRole('CLIENT')")
    @RequestMapping(value="/myborrowings" , method = RequestMethod.GET)
    public String getBorrowings(Model model) {
        Optional<User> user=null;
        final String currentUser = SecurityContextHolder.getContext().getAuthentication().getName();
        if (currentUser != "anonymousUser"){
           user = userService.findByUserName(currentUser);
        }
        Set<UserMedia> mesEmprunts = user.get().getUserMedias();
        /***********  List des users   *****************/
        model.addAttribute("mesEmprunts", mesEmprunts);
        model.addAttribute("title", "Mes emprunts");
        model.addAttribute("content", "user/emprunt");
        model.addAttribute("urlMyBorrowing","borrowings");

        return "base";
    }


    @RequestMapping(value="/borrowingedit/{userId}/{mediaId}" , method = RequestMethod.GET)
    public String updateBorrowing(@PathVariable("userId") Long userId,@PathVariable("mediaId") Long mediaId, Model model) {
        User user =userService.findByuserId(userId);
        UserMediaId pk= new UserMediaId();
        pk.setUser(userService.findByuserId(userId));
        pk.setMedia(mediaService.findByMediaId(mediaId));

        UserMedia userMedia= userMediaRepository.findByUserMediaId(pk);


        Set<UserMedia> userMedias = user.getUserMedias();
       for(UserMedia um :userMedias){
           if(um.getPk()==userMedia.getPk()){
               um.setStatus(true);
           }
       }

       user.setUserMedias(userMedias);
       userService.save(user);

        return "redirect:/borrowing";

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
    public String submitCreate(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model, WebRequest request) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getModel().values());
            model.addAttribute("action","/user/create");
            model.addAttribute("roles", roleService.findAll());
            model.addAttribute("title", "Utilisateurs");
            model.addAttribute("content", "user/add");
            model.addAttribute("urlUser","User");
            return "base";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
        LOGGER.info("---------> user token:"+user.getUserName());
        user.setEnabled(true);
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
        String action="/user/edit";
        model.addAttribute("action",action);
        /*************   Title and Content html*******************************/
        String title="Modification";
        model.addAttribute("title", title);
        String content="user/add";
        model.addAttribute("content", content);
        return "base";
    }

    @PreAuthorize("hasAnyRole('ADMIN','EMPLOYE')")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String submitEdit(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getModel().values());
            model.addAttribute("action","/user/edit");
            model.addAttribute("roles", roleService.findAll());
            model.addAttribute("title", "Utilisateurs");
            model.addAttribute("content", "user/add");
            model.addAttribute("urlUser","User");
            return "base";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        userService.save(user);
        LOGGER.info("save: "+userService.save(user));
        return "redirect:/user";
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
        model.addAttribute("subscriptions", userService.findByuserId(id).getSubscriptions());
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