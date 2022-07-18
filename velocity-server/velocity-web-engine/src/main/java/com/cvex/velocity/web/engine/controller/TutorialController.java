package com.cvex.velocity.web.engine.controller;

import com.cvex.velocity.render.writer.VelocityLayoutViewWriter;
import com.cvex.velocity.render.writer.VelocityViewWriter;
import com.cvex.velocity.web.engine.domain.Tutorial;
import com.cvex.velocity.web.engine.service.TutorialsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class TutorialController {

    private final TutorialsService tutService;
    private final VelocityLayoutViewWriter velocityViewWriter;

    public TutorialController(TutorialsService tutorialsService, VelocityLayoutViewWriter velocityViewWriter) {
        this.tutService = tutorialsService;
        this.velocityViewWriter = velocityViewWriter;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView welcomePage() throws IOException {
        final Map<String, Object> map = new HashMap<>();
        map.put("view", "template1/index");
        map.put("layout", "/WEB-INF/layouts/template1/layout.vm");

        this.velocityViewWriter.write(map);
        return new ModelAndView("index");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView listTutorialsPage(Model model) throws IOException {
        List<Tutorial> list = tutService.listTutorials();

        final Map<String, Object> map = new HashMap<>();
        map.put("view", "template1/list");
        map.put("tutorials", list);
        map.put("layout", "/WEB-INF/layouts/template1/layout.vm");

        this.velocityViewWriter.write(map);

        model.addAttribute("tutorials", list);
//        model.addAttribute("layout", "xxx.vm");

        return new ModelAndView("list");
    }

}