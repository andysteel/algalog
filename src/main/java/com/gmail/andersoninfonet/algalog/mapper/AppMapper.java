package com.gmail.andersoninfonet.algalog.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class AppMapper {
    
    private static final ModelMapper MAPPER = new ModelMapper();
    private static final AppMapper INSTANCE = new AppMapper();

    private AppMapper() {}

    public static AppMapper getInstance() {
        return INSTANCE;
    }

    public <S, T> T converter(S source, Class<T> target) {
        return MAPPER.map(source, target);
    }

    public <S, T> T converter(S source, Class<T> target, PropertyMap<S, T> propertyMap) {
        MAPPER.addMappings(propertyMap);
        return MAPPER.map(source, target);
    }
}
