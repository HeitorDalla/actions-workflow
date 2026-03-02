package com.heitor.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringDemoApplication {
    /**
     * Ponto de entrada da aplicação Spring Boot.
     * @param args argumentos da linha de comando
     */

    public static void main(final String[] args) {
        ApplicationContext context = SpringApplication.run(
                SpringDemoApplication.class,
                args);

//      Usuario usuario = context.getBean(Usuario.class);
//      usuario.code();
    }
}
