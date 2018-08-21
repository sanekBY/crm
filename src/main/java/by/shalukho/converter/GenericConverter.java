package by.shalukho.converter;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.util.function.BiFunction;

@Service
public class GenericConverter<T, B> {

    @AllArgsConstructor
    public class SimpleConverter implements DtoDboConverter<T, B> {
        private Class<T> dtoClazz;
        private Class<B> dboClazz;
        private BiFunction<B, T, T> entityToDtoFunction;
        private BiFunction<T, B, B> dtoToEntityFunction;


        @Override
        public T convertToDto(B dbo) {
            Object object = convert(dbo, dtoClazz);
            if (entityToDtoFunction != null) {
                entityToDtoFunction.apply(dbo, (T) object);
            }
            return (T) object;
        }

        @Override
        public B convertToEntity(T dto) {
            Object object = convert(dto, dboClazz);
            if (dtoToEntityFunction != null) {
                dtoToEntityFunction.apply(dto, (B) object);
            }
            return (B) object;
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
    }

    public DtoDboConverter<T, B> getConverter(Class<T> dtoClazz, Class<B> entityClazz) {
        return new SimpleConverter(dtoClazz, entityClazz, getEntityToDtoFunction(), getDtoToEntityFunction());
    }

    protected BiFunction<B, T, T> getEntityToDtoFunction() {
        return null;
    }

    protected BiFunction<T, B, B> getDtoToEntityFunction() {
        return null;
    }
}
