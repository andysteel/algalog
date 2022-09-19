package com.gmail.andersoninfonet.algalog.domain.dto.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

/**
 * {@summary A request representation of ID from a Cliente}
 * @since 0.0.1
 */
@Getter
@Setter
public class ClienteIdRequest {

    @NotNull
	private Long id;
}
