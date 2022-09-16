package com.gmail.andersoninfonet.algalog.domain.dto;

import com.gmail.andersoninfonet.algalog.domain.model.ConverterModel;
import com.gmail.andersoninfonet.algalog.mapper.AppMapper;

public interface ConverterDTO {
    
    default <E extends ConverterModel> E toEntity(Class<E> classe) {
        return AppMapper.getInstance().converter(this, classe);
    }
}
