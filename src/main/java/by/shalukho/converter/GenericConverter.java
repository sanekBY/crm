package by.shalukho.converter;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Service
public class GenericConverter<T, B> {

    @AllArgsConstructor
    public class SimpleConverter implements DtoDboConverter<T, B> {
        private Class<T> dtoClazz;
        private Class<B> dboClazz;

        @Override
        public T convertToDto(B dbo) {
            Constructor<?> ctor = null;
            Object object = null;
            try {
                ctor = dtoClazz.getConstructor();
                object = ctor.newInstance(new Object[]{});
                BeanUtils.copyProperties(dbo, object);
            } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return (T) object;
        }

        @Override
        public B convertToDbo(T dto) {
            return null;
        }

    }

    public DtoDboConverter<T, B> getConverter(Class<T> dtoClazz, Class<B> dboClazz) {
        return new SimpleConverter(dtoClazz, dboClazz);
    }
}
