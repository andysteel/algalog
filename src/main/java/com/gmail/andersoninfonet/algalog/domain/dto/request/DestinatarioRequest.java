package com.gmail.andersoninfonet.algalog.domain.dto.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

/**
 * {@summary A request representation of Destinatario}
 * @since 0.0.1
 */
@Setter
@Getter
public class DestinatarioRequest {
    
    @NotBlank
	private String nome;
	
	@NotBlank
	private String logradouro;
	
	@NotBlank
	private String numero;
	
	@NotBlank
	private String complemento;
	
	@NotBlank
	private String bairro;
}
