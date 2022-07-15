package com.cvex.velocity.web.engine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/templates")
public class TemplateRenderController {

    @GetMapping(value = {"{template}/index"})
    public ModelAndView index(@PathVariable(value = "template") String template) {
        String templatePath = "%s/index".formatted(template);

        return new ModelAndView(templatePath);
    }
}
