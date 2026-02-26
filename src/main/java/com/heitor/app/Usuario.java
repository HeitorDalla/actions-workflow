package com.heitor.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Usuario {
    @Autowired
    Aplicacao aplicacao;

    public void code () {
        aplicacao.teste();
    }
}