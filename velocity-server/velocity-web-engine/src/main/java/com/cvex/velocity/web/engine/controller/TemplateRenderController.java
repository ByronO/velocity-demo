package com.cvex.velocity.web.engine.controller;

import com.cvex.velocity.web.engine.domain.TemplateConfig;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/templates")
public class TemplateRenderController {

    @GetMapping(value = {"{template}/{view}"})
    public ModelAndView renderView(@PathVariable(value = "template") String template, @PathVariable("view") String view, Model model, HttpSession session) {
        String templatePath = "%s/%s".formatted(template, view);

        final TemplateConfig templateConfig = (TemplateConfig) session.getAttribute(TemplateConfigurationController.SESSION_CONFIG);

        model.addAttribute("template", templateConfig);
        model.addAttribute("layout", "/WEB-INF/layouts/%s/layout.vm".formatted(template));

        return new ModelAndView(templatePath);
    }
}


