package com.gmail.andersoninfonet.algalog.exception;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.gmail.andersoninfonet.algalog.exception.ErrorResponse.Campo;

@JsonInclude(Include.NON_NULL)
public record ErrorResponse(int status, LocalDateTime dataHora, String titulo, List<Campo> campos) {

    ErrorResponse(int status, LocalDateTime dataHora, String titulo) {
        this(status, dataHora, titulo, null);
    }
    public static record Campo(String nome, String mensagem) {}
}
