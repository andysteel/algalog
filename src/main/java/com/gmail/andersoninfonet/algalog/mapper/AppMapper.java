package com.gmail.andersoninfonet.algalog.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

/**
 * {@summary Config class to use ModelMapper}
 * @since 0.0.1
 */
public class AppMapper {
    
    private static final ModelMapper MAPPER = new ModelMapper();
    private static final AppMapper INSTANCE = new AppMapper();

    private AppMapper() {}

    public static AppMapper getInstance() {
        return INSTANCE;
    }

    /**
     * @param source S
     * @param target {@code Class<T>}
     * @return T
     * @since 0.0.1
     */
    public <S, T> T converter(S source, Class<T> target) {
        return MAPPER.map(source, target);
    }

    /**
     * @param source S
     * @param target {@code Class<T>}
     * @param propertyMap {@code PropertyMap<S, T>}
     * @return T
     * @since 0.0.1
     */
    public <S, T> T converter(S source, Class<T> target, PropertyMap<S, T> propertyMap) {
        MAPPER.addMappings(propertyMap);
        return MAPPER.map(source, target);
    }
}
