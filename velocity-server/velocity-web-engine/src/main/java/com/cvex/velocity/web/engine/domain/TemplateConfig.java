package com.cvex.velocity.web.engine.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TemplateConfig {
    @NotBlank
    private String brandName;
}
