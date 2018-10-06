package com.springboot.fuhui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;

@SpringBootApplication
public class FuhuiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FuhuiApplication.class, args);
    }

}
