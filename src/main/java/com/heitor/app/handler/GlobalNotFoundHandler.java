package com.heitor.app.handler;

import com.heitor.app.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice // indica que esse advice e inserido diretamente no corpo da resposta (global para tratamento de erro - retorna JSON)
public class GlobalNotFoundHandler {

    @ExceptionHandler(UserNotFoundException.class) // captura excessoes especificas (classe)
    @ResponseStatus(HttpStatus.NOT_FOUND) // define o status retornado
    public String userNotFoundHandler(UserNotFoundException user) {
        return user.getMessage();
    }


}
