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
    @NotBlank
    private String outputSuffix;

    public String getOutputPath() {
        return outputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    public String getOutputSuffix() {
        return outputSuffix;
    }

    public void setOutputSuffix(String outputSuffix) {
        this.outputSuffix = outputSuffix;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return VelocityWriterProperties.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(
                errors, "outputPath", "required-non-empty", "Output Path is required");

        ValidationUtils.rejectIfEmpty(
                errors, "outputSuffix", "required-non-empty", "Output Suffix is required");
    }
}
