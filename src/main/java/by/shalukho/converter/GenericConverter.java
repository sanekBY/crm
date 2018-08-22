package by.shalukho.converter;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.function.BiFunction;

@AllArgsConstructor
public class GenericConverter<T, B> implements DtoDboConverter<T, B> {

    private final Class<T> dtoClazz;
    private final Class<B> dboClazz;

    @Override
    public T convertToDto(B dbo) {
        Object object = convert(dbo, dtoClazz);
        if (getEntityToDtoFunction() != null) {
            getEntityToDtoFunction().apply(dbo, (T) object);
        }
        return (T) object;
    }

    @Override
    public B convertToEntity(T dto) {
        Object object = convert(dto, dboClazz);
        if (getDtoToEntityFunction() != null) {
            getDtoToEntityFunction().apply(dto, (B) object);
        }
        return (B) object;
    }

    @Override
    public Collection<T> convertAllToDto(Collection<B> entites) {
        if (entites == null) {
            return null;
        }
        return (Collection<T>) entites.stream().map(e -> convertToDto(e));
    }

    @Override
    public Collection<B> convertAllToEntity(Collection<T> dbos) {
        if (dbos == null) {
            return null;
        }
        return (Collection<B>) dbos.stream().map(d -> convertToEntity(d));
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
//    }

//    public DtoDboConverter<T, B> getConverter(Class<T> dtoClazz, Class<B> entityClazz) {
//        return new SimpleConverter(dtoClazz, entityClazz, getEntityToDtoFunction(), getDtoToEntityFunction());
//    }

    protected BiFunction<B, T, T> getEntityToDtoFunction() {
        return null;
    }

    protected BiFunction<T, B, B> getDtoToEntityFunction() {
        return null;
    }
}
