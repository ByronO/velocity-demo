package com.cvex.velocity.web.engine.controller;

import com.cvex.velocity.web.engine.domain.Tutorial;
import com.cvex.velocity.web.engine.service.TutorialsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/")
public class TutorialController {

    private final TutorialsService tutService;

    public TutorialController(TutorialsService tutorialsService) {
        this.tutService = tutorialsService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView welcomePage() {
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listTutorialsPage(Model model) {
        List<Tutorial> list = tutService.listTutorials();
        model.addAttribute("tutorials", list);
//        model.addAttribute("layout", "xxx.vm");

        return new ModelAndView("list");
    }

}