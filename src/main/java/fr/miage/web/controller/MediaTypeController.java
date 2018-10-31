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
    public String findAll(Model model) {
        final List<MediaType> mediatypes = this.mediatypeService.findAll();
        model.addAttribute("mediatypes", mediatypes);
        return "mediatype/list";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("mediatype", new MediaType());
        return "mediatype/form";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String submitCreate(@Valid @ModelAttribute MediaType mediatype, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "mediatype/form";
        }

        mediatypeService.save(mediatype);
        return "redirect:/mediatype";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(@RequestParam("id") Long id, Model model) {
        model.addAttribute("mediatype", this.mediatypeService.findById(id));
        return "mediatype/form";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String submitEdit(@Valid @ModelAttribute MediaType mediatype, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "mediatype/form";
        }

        mediatypeService.save(mediatype);
        return "redirect:/mediatype";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id) {
        mediatypeService.delete(id);
        return "redirect:/mediatype";
    }
}
