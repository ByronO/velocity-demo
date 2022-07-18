package com.cvex.velocity.render.writer;

import com.cvex.velocity.render.config.VelocityRenderProperties;
import com.cvex.velocity.render.config.VelocityWriterProperties;
import com.cvex.velocity.render.view.VelocityLayoutView;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

public class VelocityLayoutViewWriter extends VelocityViewWriter {

    public VelocityLayoutViewWriter(VelocityEngine velocityEngine, VelocityRenderProperties renderProperties, VelocityWriterProperties writerProperties) {
        super(velocityEngine, renderProperties, writerProperties);
    }

    @Override
    public void write(Map<String, Object> context) throws IOException {
        VelocityContext velocityContext = new VelocityContext(context);

        renderViewTemplate(velocityContext);

        final String outputFile = getOutputFilePath(velocityContext);

        Template template = this.getLayoutTemplate(velocityContext);

        writeToFile(template, velocityContext, outputFile);
    }

    private Template getLayoutTemplate(VelocityContext context) {
        final String templatePath = getLayoutPath(context);

        return this.velocityEngine.getTemplate(templatePath);
    }

    private String getLayoutPath(VelocityContext context) {
        if (context.containsKey("layout"))
            return context.get("layout").toString();

        return this.renderProperties.getLayoutUrl();
    }

    private void renderViewTemplate(VelocityContext context) {
        Template viewTemplate = this.getTemplate(context);

        StringWriter screenContentWriter = new StringWriter();
        viewTemplate.merge(context, screenContentWriter);

        context.put(VelocityLayoutView.DEFAULT_SCREEN_CONTENT_KEY, screenContentWriter.toString());
    }
}
