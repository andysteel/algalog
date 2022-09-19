package com.gmail.andersoninfonet.algalog.domain.dto;

import com.gmail.andersoninfonet.algalog.domain.model.ConverterModel;
import com.gmail.andersoninfonet.algalog.mapper.AppMapper;

/**
 * {@summary An interface to convert DTO to Entity}
 * @since 0.0.1
 */
public interface ConverterDTO {
    
    /**
     * {@summary Transform a DTO object into Entity object}
     * Example: {@code Car car = CarDTO.toEntity(Car.class)}
     * @param classe {@code Class<E>}
     * @return {@code <E>}
     * @since 0.0.1
     */
    default <E extends ConverterModel> E toEntity(Class<E> classe) {
        return AppMapper.getInstance().converter(this, classe);
    }
}
