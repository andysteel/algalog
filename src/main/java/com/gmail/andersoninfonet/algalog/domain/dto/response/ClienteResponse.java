package com.gmail.andersoninfonet.algalog.domain.dto.response;

import com.gmail.andersoninfonet.algalog.domain.dto.ConverterDTO;

import lombok.Data;

@Data
public class ClienteResponse implements ConverterDTO {

    private Long id;
    private String nome;
    private String telefone;
    private String email;
}
