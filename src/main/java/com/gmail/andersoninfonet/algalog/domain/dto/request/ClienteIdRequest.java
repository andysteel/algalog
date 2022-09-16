package com.gmail.andersoninfonet.algalog.domain.dto.request;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteIdRequest {

    @NotNull
	private Long id;
}
