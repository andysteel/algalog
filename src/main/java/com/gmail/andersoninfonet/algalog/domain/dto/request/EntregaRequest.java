package com.gmail.andersoninfonet.algalog.domain.dto.request;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.gmail.andersoninfonet.algalog.domain.dto.ConverterDTO;

import lombok.Getter;
import lombok.Setter;

/**
 * {@summary a request representation of Entrega}
 * @since 0.0.1
 */
@Getter
@Setter
public class EntregaRequest implements ConverterDTO {
    
    @Valid
	@NotNull
	private ClienteIdRequest cliente;
	
	@Valid
	@NotNull
	private DestinatarioRequest destinatario;
	
	@NotNull
	private BigDecimal taxa;
}
