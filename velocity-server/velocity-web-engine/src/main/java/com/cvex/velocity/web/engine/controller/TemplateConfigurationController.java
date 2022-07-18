package com.cvex.velocity.web.engine.controller;

import com.cvex.velocity.render.writer.VelocityLayoutViewWriter;
import com.cvex.velocity.web.engine.domain.TemplateConfig;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

@RestController
@RequestMapping("/templates/configuration")
public class TemplateConfigurationController {

    public static final String SESSION_CONFIG = "config";
    private final ConcurrentMap<String, TemplateConfig> sessionConfig;
    private final VelocityLayoutViewWriter viewWriter;
    @Value("#{'${render.template.list}'.split(',')}")
    private List<String> templates;

    public TemplateConfigurationController(@Qualifier("session") ConcurrentMap<String, TemplateConfig> sessionConfig,
                                           VelocityLayoutViewWriter viewWriter) {
        this.sessionConfig = sessionConfig;
        this.viewWriter = viewWriter;
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

    @PostMapping(value = "export", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> export(HttpSession session) throws IOException {
        final TemplateConfig templateConfig = Optional.ofNullable(sessionConfig.get(session.getId()))
                .orElseThrow(() -> new RuntimeException("Configuration not found for session: %s".formatted(session.getId())));

        final Map<String, Object> context = new HashMap<>();
        //template1
            //index.vm
            //list.vm
            //n template1
        //
        //cuantas plantillas tiene el folder template1
        //debemos hacer un proceso de render a disco
        //obtener las plantillas e iterar
        context.put("template", templateConfig);
        context.put("view", "template1/index");
        context.put("layout", "/WEB-INF/layouts/template1/layout.vm");

        viewWriter.write(context);

        return ResponseEntity.noContent().build();
    }
}
