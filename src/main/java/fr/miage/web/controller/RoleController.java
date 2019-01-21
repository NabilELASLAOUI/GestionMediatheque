package fr.miage.web.controller;

import fr.miage.core.entity.Role;
import fr.miage.core.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    /* l'injection de roleService */
    @Autowired
    private RoleService roleService;

    /*Cette methode renvoie la liste des roles*/
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        /***********  List des roles   *****************/
        model.addAttribute("roles", this.roleService.findAll());
        /***********  Ajout d'un role ****************/
        model.addAttribute("action","/role/create");
        model.addAttribute("Role", new Role());
        model.addAttribute("roles", roleService.findAll());
        /*************   Title and Content html*******************************/
        model.addAttribute("title", "Roles");
        model.addAttribute("content", "role/index");
        model.addAttribute("urlRole","roles");
        return "base";
    }

    /*cette methode sert a la creation d'un role*/
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String submitCreate(@Valid @ModelAttribute Role role, BindingResult bindingResult, Model model) {
        //LOGGER.info("******* create role *******");
        if (bindingResult.hasErrors()) {
            /***********  errors role create ****************/
            //LOGGER.info("-----------> errors role create");
            model.addAttribute("action","/role/create");
            model.addAttribute("Role", role);
            /*************   Title and Content html*******************************/
            model.addAttribute("title", "Roles");
            model.addAttribute("content", "role/index");
            return "base";
        }
        roleService.save(role);
        return "redirect:/role";
    }

    /*cette methode sert a la recuperation des données d'un role pour les modifier*/
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") Long id, Model model) {
        model.addAttribute("Role", roleService.findById(id));
        model.addAttribute("roles", roleService.findAll());
        String action="/role/create";
        model.addAttribute("action",action);
        /*************   Title and Content html*******************************/
        String title="Modification";
        model.addAttribute("title", title);
        String content="role/index";
        model.addAttribute("content", content);
        return "base";
    }

    /*Cette methode supprime un role */
    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        roleService.delete(id);
        return "redirect:/role";
    }
}
