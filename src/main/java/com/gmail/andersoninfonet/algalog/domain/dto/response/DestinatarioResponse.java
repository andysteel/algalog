package com.gmail.andersoninfonet.algalog.domain.dto.response;

import lombok.Data;

/**
 * {@summary A response representation of Destinatario}
 * @since 0.0.1
 */
@Data
public class DestinatarioResponse {
    
    private String nome;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
}
