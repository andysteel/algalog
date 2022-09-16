package com.gmail.andersoninfonet.algalog.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;
    
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
            final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        
        final List<ErrorResponse.Campo> campos = ex.getAllErrors().stream().map(error -> {
            final String campo = ((FieldError)error).getField();
            final String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
            return new ErrorResponse.Campo(campo, mensagem);
        }).toList();
        
        return super.handleExceptionInternal(ex, new ErrorResponse(status.value(), LocalDateTime.now(), "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente", campos), headers, status, request);
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<Object> handleNegocioException(NegocioException ex, WebRequest request) {
        return super.handleExceptionInternal(ex, new ErrorResponse(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), ex.getMessage()), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
