package com.gmail.andersoninfonet.algalog.domain.dto.request;

import javax.validation.constraints.NotBlank;

import com.gmail.andersoninfonet.algalog.domain.dto.ConverterDTO;

import lombok.Data;

/**
 * {@summary a request representation of Ocorrencia}
 * @since 0.0.1
 */
@Data
public class OcorrenciaRequest implements ConverterDTO {
    
    @NotBlank
    private String descricao;

}
