package com.gmail.andersoninfonet.algalog.domain.model;

import com.gmail.andersoninfonet.algalog.domain.dto.ConverterDTO;
import com.gmail.andersoninfonet.algalog.mapper.AppMapper;

/**
 * {@summary An interface to convert Entity to DTO}
 * @since 0.0.1
 */
public interface ConverterModel {
    
    /**
     * {@summary Transform an Entity object into DTO object}
     * Example: {@code CarDTO carDTO = Car.toDTO(CarDTO.class)}
     * @param classe {@code Class<D>}
     * @return {@code <D>}
     * @since 0.0.1
     */
    default  <D extends ConverterDTO> D toDTO(Class<D> classe) {
        return AppMapper.getInstance().converter(this, classe);
    }
}
