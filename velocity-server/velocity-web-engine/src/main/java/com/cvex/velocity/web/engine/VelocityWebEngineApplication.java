package com.cvex.velocity.web.engine;

import com.cvex.velocity.render.annotation.EnableVelocityRender;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableVelocityRender
public class VelocityWebEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(VelocityWebEngineApplication.class, args);
    }

}
