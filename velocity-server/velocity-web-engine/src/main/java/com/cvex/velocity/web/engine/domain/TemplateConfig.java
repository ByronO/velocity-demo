package com.cvex.velocity.web.engine.domain;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class TemplateConfig {
    @NotBlank
    private String brandName;
}
