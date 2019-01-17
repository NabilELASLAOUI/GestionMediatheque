package fr.miage.web.controller;

import fr.miage.core.entity.MediaType;
import fr.miage.core.entity.SubscriptionType;
import fr.miage.core.service.MediaTypeService;
import fr.miage.core.service.SubscriptionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/subscriptiontype")
public class SubscriptionTypeController {

    @Autowired
    SubscriptionTypeService subscriptionTypeService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        /***********  List des types de media   *****************/
       // model.addAttribute("mediatypes", this.mediatypeService.findAll());
        /***********  Ajout d'un type de media ****************/
        model.addAttribute("action","/subscriptiontype/create");
        model.addAttribute("SubscriptionType", new SubscriptionType());
        model.addAttribute("subscriptiontypes", subscriptionTypeService.findAll());
        /*************   Title and Content html*******************************/
        model.addAttribute("title", "Subscription types");
        model.addAttribute("content", "subscriptiontype/index");
        model.addAttribute("urlSubscriptiontype","subscriptiontypes");

        return "base";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String submitCreate(@Valid @ModelAttribute SubscriptionType subscriptiontype, BindingResult bindingResult, Model model) {
        //LOGGER.info("******* create User *******");
        if (bindingResult.hasErrors()) {
            /***********  errors media type ****************/
            //LOGGER.info("-----------> errors media type create");
            model.addAttribute("action","/subscriptionType/create");
            model.addAttribute("SubscriptionType", subscriptiontype);
            /*************   Title and Content html*******************************/
            model.addAttribute("title", "Subscriptiontypes");
            model.addAttribute("content", "subscriptiontype/index");
            return "base";
        }

        subscriptionTypeService.save(subscriptiontype);
        return "redirect:/subscriptiontype";
    }
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") Long id, Model model) {
        model.addAttribute("SubscriptionType", subscriptionTypeService.findByTypeId(id));
        model.addAttribute("subscriptiontypes", subscriptionTypeService.findAll());
        String action="/subscriptiontype/create";
        model.addAttribute("action",action);
        /*************   Title and Content html*******************************/
        String title="Modification";
        model.addAttribute("title", title);
        String content="subscriptiontype/index";
        model.addAttribute("content", content);
        return "base";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        subscriptionTypeService.delete(id);
        return "redirect:/subscriptiontype";
    }

}
