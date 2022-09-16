package com.gmail.andersoninfonet.algalog.domain.dto.response;

import lombok.Data;

@Data
public class DestinatarioResponse {
    
    private String nome;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
}
