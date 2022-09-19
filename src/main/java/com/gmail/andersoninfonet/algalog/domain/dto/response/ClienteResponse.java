package com.gmail.andersoninfonet.algalog.domain.dto.response;

import com.gmail.andersoninfonet.algalog.domain.dto.ConverterDTO;

import lombok.Data;

/**
 * {@summary A response representation of Cliente}
 * @since 0.0.1
 */
@Data
public class ClienteResponse implements ConverterDTO {

    private Long id;
    private String nome;
    private String telefone;
    private String email;
}
