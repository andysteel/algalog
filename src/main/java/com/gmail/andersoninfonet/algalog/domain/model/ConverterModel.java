package com.gmail.andersoninfonet.algalog.domain.model;

import com.gmail.andersoninfonet.algalog.domain.dto.ConverterDTO;
import com.gmail.andersoninfonet.algalog.mapper.AppMapper;

public interface ConverterModel {
    
    default  <D extends ConverterDTO> D toDTO(Class<D> classe) {
        return AppMapper.getInstance().converter(this, classe);
    }
}
