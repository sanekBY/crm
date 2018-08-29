package by.shalukho.converter;

import by.shalukho.dto.ConnectedDto;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

public class GenericConverter<T, B> implements DtoDboConverter<T, B> {

    private Class<T> dtoClazz;
    private final Class<B> entityClazz;

    public GenericConverter(final Class<B> entityClazz) {
        this.entityClazz = entityClazz;
    }

    @Override
    public T convertToDto(B entity) {
        T dto = (T) convert(entity, getDtoClass());
        getEntityToDtoFunction().apply(entity, dto);
        return dto;
    }

    @Override
    public B convertToEntity(T dto) {
        B entity = (B) convert(dto, entityClazz);
        getDtoToEntityFunction().apply(dto, entity);
        return entity;
    }

    @Override
    public List<T> convertAllToDto(Collection<B> entites) {
        if (entites == null) {
            return null;
        }
        return entites.stream().map(e -> convertToDto(e)).collect(Collectors.toList());
    }

    @Override
    public List<B> convertAllToEntity(Collection<T> dbos) {
        if (dbos == null) {
            return null;
        }
        return dbos.stream().map(d -> convertToEntity(d)).collect(Collectors.toList());
    }

    private Object convert(Object dto, Class clazz) {
        Constructor<?> ctor;
        Object object = null;
        try {
            ctor = clazz.getConstructor();
            object = ctor.newInstance(new Object[]{});
            BeanUtils.copyProperties(dto, object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    private BiFunction<B, T, T> getEntityToDtoFunction() {
        BiFunction<B, T, T> function = (b, t) -> {
            return extraConvertToDto(b, t);
        };
        return function;
    }

    protected T extraConvertToDto(final B b, final T t) {
        return t;
    }

    private BiFunction<T, B, B> getDtoToEntityFunction() {
        BiFunction<T, B, B> function = (t, b) -> {
            return extraConvertToEntity(t, b);
        };
        return function;
    }

    private Class<T> getDtoClass() {
        if (dtoClazz == null) {
            dtoClazz = (Class<T>) entityClazz.getAnnotation(ConnectedDto.class).value();
        }
        return dtoClazz;
    }

    protected B extraConvertToEntity(final T t, final B b) {
        return b;
    }

    public Class<B> getEntityClazz() {
        return entityClazz;
    }
}
