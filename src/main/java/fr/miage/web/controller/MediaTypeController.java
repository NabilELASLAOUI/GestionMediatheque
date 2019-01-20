package fr.miage.web.controller;

import fr.miage.core.entity.MediaType;
import fr.miage.core.service.MediaTypeService;
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
@RequestMapping("/mediatype")
public class MediaTypeController {

    @Autowired
    MediaTypeService mediatypeService;

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        /***********  List des types de media   *****************/
       // model.addAttribute("mediatypes", this.mediatypeService.findAll());
        /***********  Ajout d'un type de media ****************/
        model.addAttribute("action","/mediatype/create");
        model.addAttribute("MediaType", new MediaType());
        model.addAttribute("mediatypes", mediatypeService.findAll());
        /*************   Title and Content html*******************************/
        model.addAttribute("title", "Media types");
        model.addAttribute("content", "mediatype/index");
        model.addAttribute("urlMediatype","mediatypes");

        return "base";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String submitCreate(@Valid @ModelAttribute MediaType mediaType, BindingResult bindingResult, Model model) {
        //LOGGER.info("******* create User *******");
        if (bindingResult.hasErrors()) {
            /***********  errors media type ****************/
            //LOGGER.info("-----------> errors media type create");
            model.addAttribute("action","/mediaType/create");
            model.addAttribute("MediaType", mediaType);
            /*************   Title and Content html*******************************/
            model.addAttribute("title", "Mediatypes");
            model.addAttribute("content", "mediatype/index");
            return "base";
        }

        mediatypeService.save(mediaType);
        return "redirect:/mediatype";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") Long id, Model model) {
        model.addAttribute("MediaType", mediatypeService.findByTypeId(id));
        model.addAttribute("mediatypes", mediatypeService.findAll());
        String action="/mediatype/create";
        model.addAttribute("action",action);
        /*************   Title and Content html*******************************/
        String title="Modification";
        model.addAttribute("title", title);
        String content="mediatype/index";
        model.addAttribute("content", content);
        return "base";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        mediatypeService.delete(id);
        return "redirect:/mediatype";
    }

}
