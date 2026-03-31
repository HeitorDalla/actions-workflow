package com.heitor.app.handler;

import com.heitor.app.dto.common.ErroResponseDTO;
import com.heitor.app.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler({
            UserNotFoundException.class,
            BookNotFoundException.class,
            LoanNotFoundException.class,
            ReserveNotFoundException.class,
            FineNotFoundException.class
    })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErroResponseDTO handleNotFound(RuntimeException ex) {
        return new ErroResponseDTO(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage()
        );
    }

    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ErroResponseDTO handleForbidden(RuntimeException ex) {
        return new ErroResponseDTO(
                LocalDateTime.now(),
                HttpStatus.FORBIDDEN.value(),
                ex.getMessage()
        );
    }
}
