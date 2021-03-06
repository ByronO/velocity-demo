package com.cvex.velocity.render.config;

import com.cvex.velocity.render.view.VelocityLayoutView;
import com.cvex.velocity.render.resolver.VelocityLayoutViewResolver;
import com.cvex.velocity.render.writer.VelocityLayoutViewWriter;
import com.cvex.velocity.render.writer.VelocityViewWriter;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.spring.VelocityEngineFactoryBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.ViewResolver;

import java.util.Optional;
import java.util.Properties;

@Order(1)
@Configuration
@EnableConfigurationProperties({VelocityRenderProperties.class, VelocityWriterProperties.class})
public class VelocityRenderConfig {

    @Bean
    public ViewResolver viewResolver(VelocityRenderProperties properties) {
        final VelocityLayoutViewResolver bean = new VelocityLayoutViewResolver();
        bean.setCache(Optional.of(properties.isUseCache()).orElse(true));
        bean.setViewClass(VelocityLayoutView.class);
        bean.setPrefix(properties.getPrefix()); //"/WEB-INF/views/"
        bean.setLayoutUrl(properties.getLayoutUrl()); //"/WEB-INF/layouts/layout.vm"
        bean.setSuffix(properties.getSuffix());
        bean.setScreenContentKey(properties.getScreenContentKey());

        return bean;
    }

    @Bean
    public VelocityEngineFactoryBean velocityEngineFactory() {
        final VelocityEngineFactoryBean velocityEngineFactoryBean = new VelocityEngineFactoryBean();

        final Properties properties = new Properties();
        properties.setProperty("resource.loaders", "class");
        properties.setProperty("resource.loader.class.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        velocityEngineFactoryBean.setVelocityProperties(properties);

        return velocityEngineFactoryBean;
    }

    @Bean
    public VelocityEngine velocityEngine(VelocityEngineFactoryBean velocityEngineFactoryBean) {
        return velocityEngineFactoryBean.getObject();
    }

    @Bean
    public VelocityLayoutViewWriter velocityLayoutViewWriter(VelocityEngine velocityEngine, VelocityRenderProperties renderProperties,
                                                             VelocityWriterProperties writerProperties) {
        return new VelocityLayoutViewWriter(velocityEngine, renderProperties, writerProperties);
    }
}
