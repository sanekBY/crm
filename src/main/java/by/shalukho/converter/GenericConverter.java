package by.shalukho.converter;

import by.shalukho.dto.ConnectedDto;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

/**
 * Generic converter for entities and dtos.
 * Dto should be placen in @ConnectedDto annotation
 * Converts all fields using BeanUtils.
 * Additional logic can be implemented in extraConvertToEntity and extraConvertToDto methods
 *
 * @param <T> - DTO class
 * @param <B> - Entity class
 */
public class GenericConverter<T, B> implements DtoDboConverter<T, B> {

    private Class<T> dtoClazz;
    private final Class<B> entityClazz;

    public GenericConverter(final Class<B> entityClazz) {
        this.entityClazz = entityClazz;
    }

    @Override
    public T convertToDto(final B entity) {
        final T dto = (T) convert(entity, getDtoClass());
        getEntityToDtoFunction().apply(entity, dto);
        return dto;
    }

    @Override
    public B convertToEntity(final T dto) {
        final B entity = (B) convert(dto, entityClazz);
        getDtoToEntityFunction().apply(dto, entity);
        return entity;
    }

    @Override
    public List<T> convertAllToDto(final Collection<B> entites) {
        if (entites == null) {
            return null;
        }
        return entites.stream().map(e -> convertToDto(e)).collect(Collectors.toList());
    }

    @Override
    public List<B> convertAllToEntity(final Collection<T> dbos) {
        if (dbos == null) {
            return null;
        }
        return dbos.stream().map(d -> convertToEntity(d)).collect(Collectors.toList());
    }

    private Object convert(final Object dto, final Class clazz) {
        final Constructor<?> ctor;
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
        final BiFunction<B, T, T> function = (b, t) -> extraConvertToDto(b, t);
        return function;
    }

    protected T extraConvertToDto(final B b, final T t) {
        return t;
    }

    private BiFunction<T, B, B> getDtoToEntityFunction() {
        final BiFunction<T, B, B> function = (t, b) -> extraConvertToEntity(t, b);
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
