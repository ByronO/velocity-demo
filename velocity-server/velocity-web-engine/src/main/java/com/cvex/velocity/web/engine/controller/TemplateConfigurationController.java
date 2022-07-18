package com.cvex.velocity.web.engine.controller;

import com.cvex.velocity.web.engine.domain.TemplateConfig;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping("/templates/configuration")
public class TemplateConfigurationController {

    public static final String SESSION_CONFIG = "config";
    private final ConcurrentMap<String, TemplateConfig> sessionConfig;
    @Value("#{'${render.template.list}'.split(',')}")
    private List<String> templates;

    public TemplateConfigurationController(@Qualifier("session") ConcurrentMap<String, TemplateConfig> sessionConfig) {
        this.sessionConfig = sessionConfig;
    }

    @GetMapping("list")
    public List<String> getCurrentTemplates() {
        return this.templates;
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createTemplateConfiguration(@RequestBody TemplateConfig config, HttpSession session) {
        sessionConfig.put(session.getId(), config);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    public ResponseEntity<TemplateConfig> getCurrentConfig(HttpSession httpSession) {
        final TemplateConfig config = (TemplateConfig) httpSession.getAttribute(SESSION_CONFIG);

        return ResponseEntity.ok(config);
    }
}
