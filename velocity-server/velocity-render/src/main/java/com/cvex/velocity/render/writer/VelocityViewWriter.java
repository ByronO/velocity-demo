package com.cvex.velocity.render.writer;

import com.cvex.velocity.render.config.VelocityRenderProperties;
import com.cvex.velocity.render.config.VelocityWriterProperties;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.util.Assert;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;

public class VelocityViewWriter {

    final VelocityEngine velocityEngine;
    final VelocityRenderProperties renderProperties;
    private final VelocityWriterProperties properties;

    public VelocityViewWriter(VelocityEngine velocityEngine, VelocityRenderProperties renderProperties, VelocityWriterProperties writerProperties) {
        this.velocityEngine = velocityEngine;
        this.renderProperties = renderProperties;
        this.properties = writerProperties;
    }

    public void write(Map<String, Object> context) throws IOException {
        VelocityContext velocityContext = new VelocityContext(context);

        final String outputFile = getOutputFilePath(velocityContext);

        Template template = this.getTemplate(velocityContext);

        writeToFile(template, velocityContext, outputFile);
    }

    public void writeToFile(Template template, VelocityContext context, String outputPath) throws IOException {
        FileWriter fileWriter = new FileWriter(outputPath);
        template.merge(context, fileWriter);
        fileWriter.flush();
        fileWriter.close();
    }

    String getOutputFilePath(VelocityContext context) throws IOException {
        String destinationDir = Optional.ofNullable(context.get("destination"))
                .map(Object::toString).orElse("");

        final String view = this.getView(context);
        final String viewName = view.substring(view.lastIndexOf("/") + 1);

        final Path outputDirectory = Files.createDirectories(
                Paths.get(getOutputPath().toString(), destinationDir)
        );

        return Paths.get(outputDirectory.toString(),
                viewName + properties.getOutputSuffix()).toString();
    }

    Template getTemplate(VelocityContext context) {
        final String templatePath = getViewPath(context);

        return this.velocityEngine.getTemplate(templatePath);
    }

    private String getViewPath(VelocityContext context) {
        final String view = getView(context);

        //existing source + name + the actual suffix or extension of the template
        return Paths.get(renderProperties.getPrefix(),
                view + renderProperties.getSuffix()).toString();
    }

    String getView(VelocityContext context) {
        Assert.isTrue(context.containsKey("view"), "The view name is required");

        return (String) context.get("view");
    }

    private Path getOutputPath() {
        return Paths.get(this.properties.getOutputPath()).toAbsolutePath().normalize();
    }
}
