package com.heitor.app;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    /**
     * Retorna saudação simples
     * @return mensagem de boas-vindas
     */

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }
}
