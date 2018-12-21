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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
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
    }
}
