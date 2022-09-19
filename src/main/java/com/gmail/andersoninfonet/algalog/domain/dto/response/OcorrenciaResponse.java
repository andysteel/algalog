package com.gmail.andersoninfonet.algalog.domain.dto.response;

import java.time.LocalDateTime;

import com.gmail.andersoninfonet.algalog.domain.dto.ConverterDTO;

import lombok.Data;

/**
 * {@summary A response representation of Ocorrencia}
 * @since 0.0.1
 */
@Data
public class OcorrenciaResponse implements ConverterDTO {
    
    private Long id;
    private String descricao;
    private LocalDateTime dataRegistro;
}
