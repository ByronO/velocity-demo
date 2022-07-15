package com.cvex.velocity.render.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Validated
@ConfigurationProperties(prefix = "velocity.writer")
public class VelocityWriterProperties implements Validator {
    @NotBlank
    private String outputPath;

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return VelocityWriterProperties.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(
                errors, "outputPath", "required-non-empty", "Output Path is required");
    }
}
