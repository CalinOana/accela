package com.accela.components.cast;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Component
public class ModelMapperExtended {

    private final ModelMapper modelMapper;

    public ModelMapperExtended(final ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <T> List<T> mapAll(Iterable<?> source, Class<T> targetType) {
        ArrayList<T> result = new ArrayList<>();
        if (source != null) {
            for (Object o : source) {
                result.add(modelMapper.map(o, targetType));
            }
        }
        return result;
    }

    public <D> D map(Object source, Class<D> destinationType) {
        if (source == null) {
            return null;
        }
        return modelMapper.map(source, destinationType);
    }

    public <D> D map(Object source, Type type) {
        if (source == null) {
            return null;
        }
        return modelMapper.map(source, type);
    }

}
