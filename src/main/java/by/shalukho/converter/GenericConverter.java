package by.shalukho.converter;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

@AllArgsConstructor
public class GenericConverter<T, B> implements DtoDboConverter<T, B> {

    private final Class<T> dtoClazz;

    private final Class<B> entityClazz;

    @Override
    public T convertToDto(B entity) {
        T dto = (T) convert(entity, dtoClazz);
        if (getEntityToDtoFunction() != null) {
            getEntityToDtoFunction().apply(entity, (T) dto);
        }
        return dto;
    }

    @Override
    public B convertToEntity(T dto) {
        B entity = (B) convert(dto, entityClazz);
        if (getDtoToEntityFunction() != null) {
            getDtoToEntityFunction().apply(dto, (B) entity);
        }
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

    protected BiFunction<B, T, T> getEntityToDtoFunction() {
        return null;
    }

    protected BiFunction<T, B, B> getDtoToEntityFunction() {
        return null;
    }

    public Class<B> getEntityClazz() {
        return entityClazz;
    }
}
