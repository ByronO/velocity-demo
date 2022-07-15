package com.cvex.velocity.render.writer;

import com.cvex.velocity.render.config.VelocityWriterProperties;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

@Component
@EnableConfigurationProperties(VelocityWriterProperties.class)
public class VelocityViewWriter {

    private final VelocityEngine velocityEngine;
    private final VelocityWriterProperties velocityWriterProperties;

    public VelocityViewWriter(VelocityEngine velocityEngine, VelocityWriterProperties velocityWriterProperties) {

        this.velocityEngine = velocityEngine;
        this.velocityWriterProperties = velocityWriterProperties;
    }

    public void staticFileWriter(Map<String, Object> values){
        Template template = this.velocityEngine.getTemplate("/WEB-INF/views/index.vm");

        VelocityContext velocityContext = new VelocityContext(values);

        try {
            FileWriter fileWriter = new FileWriter(new File("%s/index.html".formatted(this.velocityWriterProperties.getOutputPath())));
            template.merge(velocityContext, fileWriter);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
