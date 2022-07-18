package com.cvex.velocity.render.config;


import com.cvex.velocity.render.view.VelocityLayoutView;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;

import org.springframework.validation.Validator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Validated
@ConfigurationProperties(prefix = "velocity.render")
public class VelocityRenderProperties implements Validator {
    @NotBlank
    private String prefix;
    @NotBlank
    private String suffix = ".vm";
    @NotBlank
    private String layoutUrl = VelocityLayoutView.DEFAULT_LAYOUT_URL;
    @NotBlank
    private String screenContentKey = VelocityLayoutView.DEFAULT_SCREEN_CONTENT_KEY;

    private boolean useCache = true;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public String getLayoutUrl() {
        return layoutUrl;
    }

    public void setLayoutUrl(String layoutUrl) {
        this.layoutUrl = layoutUrl;
    }

    public String getScreenContentKey() {
        return screenContentKey;
    }

    public void setScreenContentKey(String screenContentKey) {
        this.screenContentKey = screenContentKey;
    }

    public boolean isUseCache() {
        return useCache;
    }

    public void setUseCache(boolean useCache) {
        this.useCache = useCache;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return VelocityRenderProperties.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(
                errors, "prefix", "required-non-empty", "Prefix is required");
        ValidationUtils.rejectIfEmpty(
                errors, "suffix", "required-non-empty", "Suffix is required");
        ValidationUtils.rejectIfEmpty(
                errors, "layoutUrl", "required-non-empty", "Layout URL is required");
        ValidationUtils.rejectIfEmpty(
                errors, "screenContentKey", "required-non-empty", "Screen Content Key is required");
    }
}
