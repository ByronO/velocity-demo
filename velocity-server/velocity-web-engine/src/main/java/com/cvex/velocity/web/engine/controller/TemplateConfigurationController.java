package com.cvex.velocity.web.engine.controller;

import com.cvex.velocity.web.engine.domain.TemplateConfig;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/templates/configuration")
@AllArgsConstructor
public class TemplateConfigurationController {

    private final String SESSION_CONFIG = "config";
    @Value("#{'${render.template.list}'.split(',')}")
    private List<String> templates;

    @GetMapping("list")
    public List<String> getCurrentTemplates() {
        return templates;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTemplateConfiguration(@RequestBody TemplateConfig config, HttpSession session) {
        session.setAttribute(SESSION_CONFIG, config);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    public ResponseEntity<TemplateConfig> getCurrentConfig(HttpSession httpSession) {

        final TemplateConfig config = (TemplateConfig) httpSession.getAttribute(SESSION_CONFIG);

//        if (config == null) {
//
//        }

        return ResponseEntity.ok(config);
    }
}
