package com.cvex.velocity.web.engine.controller;

import com.cvex.velocity.render.writer.VelocityViewWriter;
import com.cvex.velocity.web.engine.domain.Tutorial;
import com.cvex.velocity.web.engine.service.TutorialsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/")
public class TutorialController {

    private final TutorialsService tutService;
    private final VelocityViewWriter velocityViewWriter;

    public TutorialController(TutorialsService tutorialsService, VelocityViewWriter velocityViewWriter) {
        this.tutService = tutorialsService;
        this.velocityViewWriter = velocityViewWriter;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcomePage() {
        this.velocityViewWriter.staticFileWriter(new HashMap<>());
        return "index";
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listTutorialsPage(Model model) {
        List<Tutorial> list = tutService.listTutorials();
        model.addAttribute("tutorials", list);
        return "list";
    }

}