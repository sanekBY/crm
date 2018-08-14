package by.shalukho.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@Service
public class GenericConverter<T, B> implements DtoDboConverter<T, B> {

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

    public void setDtoClazz(Class<T> dtoClazz) {
        this.dtoClazz = dtoClazz;
    }

    public void setDboClazz(Class<B> dboClazz) {
        this.dboClazz = dboClazz;
    }


//    @Override
//    public UserDto convertToDto(final UserEntity dbo) {
//        final UserDto userDto = new UserDto();
//        BeanUtils.copyProperties(dbo, userDto);
//        return userDto;
//    }
//
//    @Override
//    public UserEntity convertToDbo(final UserDto dto) {
//        final UserEntity userDbo = new UserEntity();
//        BeanUtils.copyProperties(dto, userDbo);
//        return userDbo;
//    }
}
