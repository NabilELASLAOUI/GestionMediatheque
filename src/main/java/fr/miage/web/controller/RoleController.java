package fr.miage.web.controller;

import fr.miage.core.entity.Role;
import fr.miage.core.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping(method = RequestMethod.GET)
    public String findAll(Model model) {
        final List<Role> role = this.roleService.findAll();
        model.addAttribute("role", role);
        return "role/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("role", new Role());
        return "role/form";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String submitCreate(@Valid @ModelAttribute Role role, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "role/form";
        }

        roleService.save(role);
        return "redirect:/role";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") Long id, Model model) {
        model.addAttribute("role", this.roleService.findById(id));
        return "role/form";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String submitEdit(@Valid @ModelAttribute Role role, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "role/form";
        }

        roleService.save(role);
        return "redirect:/role";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        roleService.delete(id);
        return "redirect:/role";
    }
}
