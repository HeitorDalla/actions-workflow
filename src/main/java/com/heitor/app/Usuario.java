package com.heitor.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Usuario {
    /**
     * Referência para a aplicação.
     */
    @Autowired
    private Aplicacao aplicacao;

    /**
     * Método de teste de aplicação
     */
    public void code() {
        aplicacao.teste();
    }

    /* Métodos Getters e Setters */

    public Aplicacao getAplicacao() {
        return aplicacao;
    }

    public void setAplicacao(Aplicacao aplicacao) {
        this.aplicacao = aplicacao;
    }
}
