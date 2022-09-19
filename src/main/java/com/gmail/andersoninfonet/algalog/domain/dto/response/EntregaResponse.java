package com.gmail.andersoninfonet.algalog.domain.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.gmail.andersoninfonet.algalog.domain.dto.ConverterDTO;
import com.gmail.andersoninfonet.algalog.domain.model.enums.StatusEntrega;

import lombok.Data;

/**
 * {@summary A response representation of Entrega}
 * @since 0.0.1
 */
@Data
public class EntregaResponse implements ConverterDTO {
    
    private Long id;
    private String nomeCliente;
    private DestinatarioResponse destinatario;
    private BigDecimal taxa;
    private StatusEntrega status;
    private LocalDateTime dataPedido;
    private LocalDateTime dataFinalizacao;
}
