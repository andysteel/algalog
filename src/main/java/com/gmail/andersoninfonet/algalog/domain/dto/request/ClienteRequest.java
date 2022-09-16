package com.gmail.andersoninfonet.algalog.domain.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.gmail.andersoninfonet.algalog.domain.dto.ConverterDTO;

import lombok.Data;

@Data
public class ClienteRequest implements ConverterDTO {
    
    @NotBlank
    @Size(max = 155)
    private String nome;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(max = 20)
    private String telefone;
}
