package fr.miage.web.controller;

import fr.miage.core.entity.MediaType;
import fr.miage.core.service.MediaTypeService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        /***********  List des roles   *****************/
        model.addAttribute("mediatypes", this.mediatypeService.findAll());
        /***********  Ajout d'un role ****************/
        model.addAttribute("action","/mediatype/create");
        model.addAttribute("MediaType", new MediaType());
        model.addAttribute("mediatypes", mediatypeService.findAll());
        /*************   Title and Content html*******************************/
        model.addAttribute("title", "Mediatypes");
        model.addAttribute("content", "media/index");
        model.addAttribute("urlMediatype","mediatypes");
        return "base";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String submitCreate(@Valid @ModelAttribute MediaType mediaType, BindingResult bindingResult, Model model) {
        //LOGGER.info("******* create User *******");
        if (bindingResult.hasErrors()) {
            /***********  errors user create ****************/
            //LOGGER.info("-----------> errors user create");
            model.addAttribute("action","/mediaType/create");
            model.addAttribute("Mediatype", mediaType);
            /*************   Title and Content html*******************************/
            model.addAttribute("title", "Mediatypes");
            model.addAttribute("content", "mediatype/index");
            return "base";
        }
        // TO DO
        // Encrypt password
        // user.setUserPassword();
        mediatypeService.save(mediaType);
        return "redirect:/mediaType";
    }
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") Long id, Model model) {
        model.addAttribute("Mediatype", mediatypeService.findById(id));
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
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        mediatypeService.delete(id);
        return "redirect:/mediatype";
    }
    /*
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        final List<MediaType> mediatypes = this.mediatypeService.findAll();
        String action="/mediatype/add";
        model.addAttribute("action",action);
        model.addAttribute("mediatypes", mediatypes);
        model.addAttribute("mediatype", new MediaType());
        String content="media/typemedia";
        String title="Type de Media";
        model.addAttribute("title", title);
        model.addAttribute("content", content);
        return "base";
    }

   /* @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String submitCreate(@Valid @ModelAttribute MediaType mediatype, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            final List<MediaType> mediatypes = this.mediatypeService.findAll();
            model.addAttribute("mediatypes", mediatypes);
            return "media/typemedia";
        }

        mediatypeService.save(mediatype);
        return "redirect:/create";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") Long id, Model model) {
        String action="edit";
        model.addAttribute(action);
        model.addAttribute("mediatype", this.mediatypeService.findById(id));
        return "media/typemedia";
    }

    @RequestMapping(value = "/doEdit", method = RequestMethod.POST)
    public String submitEdit(@Valid @ModelAttribute MediaType mediatype, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            final List<MediaType> mediatypes = this.mediatypeService.findAll();
            model.addAttribute("mediatypes", mediatypes);
            return "media/typemedia";
        }

        mediatypeService.save(mediatype);
        return "redirect:/types";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        mediatypeService.delete(id);
        return "redirect:/create";
    }*/
}
