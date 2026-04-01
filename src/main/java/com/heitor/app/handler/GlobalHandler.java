package com.heitor.app.handler;

import com.heitor.app.exception.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class GlobalHandler {

    @ExceptionHandler({
            UserNotFoundException.class,
            BookNotFoundException.class,
            LoanNotFoundException.class,
            ReserveNotFoundException.class,
            FineNotFoundException.class
    })
    public ProblemDetail handleNotFound(RuntimeException ex, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.NOT_FOUND,
                ex.getMessage()
        );

        problemDetail.setType(URI.create("https://api.projeto.com/errors/not-found"));
        problemDetail.setTitle("Resource not found");
        problemDetail.setInstance(URI.create(request.getRequestURI()));

        return problemDetail;
    }

    @ExceptionHandler(BusinessException.class)
    public ProblemDetail handleForbidden(BusinessException ex, HttpServletRequest request) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                HttpStatus.FORBIDDEN,
                ex.getMessage()
        );

        problemDetail.setType(URI.create("https://api.projeto.com/errors/business-rule"));
        problemDetail.setTitle("Business rule violation");
        problemDetail.setInstance(URI.create(request.getRequestURI()));

        return problemDetail;
    }
}
