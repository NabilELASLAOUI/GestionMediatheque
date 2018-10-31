package fr.miage.web.controller;

import fr.miage.core.entity.MediaType;
import fr.miage.core.entity.User;
import fr.miage.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.*;

import java.util.List;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String findAll(Model model) {
        final List<User> user = this.userService.findAll();
        model.addAttribute("user", user);
        return "user/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "user/form";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String submitCreate(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "user/form";
        }

        userService.save(user);
        return "redirect:/user";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") Long id, Model model) {
        model.addAttribute("user", this.userService.findById(id));
        return "user/form";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String submitEdit(@Valid @ModelAttribute User user, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "user/form";
        }

        userService.save(user);
        return "redirect:/user";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/user";
    }
}

