package fr.miage.web.controller;

import fr.miage.core.entity.Customer;
import fr.miage.core.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(method = RequestMethod.GET)
    public String findAll(Model model) {
        final List<Customer> customers = this.customerService.findAll();
        model.addAttribute("customers", customers);
        return "customer/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer/form";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String submitCreate(@Valid @ModelAttribute Customer customer, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
//            model.addAttribute("customer", customer);
            return "customer/form";
        }

        customerService.save(customer);
        return "redirect:/customer";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") Long id, Model model) {
        model.addAttribute("customer", this.customerService.findById(id));
        return "customer/form";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String submitEdit(@Valid @ModelAttribute Customer customer, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
//            model.addAttribute("customer", customer);
            return "customer/form";
        }

        customerService.save(customer);
        return "redirect:/customer";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        customerService.delete(id);
        return "redirect:/customer";
    }
}
