package com.cvex.velocity.render.annotation;

import com.cvex.velocity.render.config.VelocityRenderConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(VelocityRenderConfig.class)
public @interface EnableVelocityRender {
}
