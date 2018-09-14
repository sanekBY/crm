package by.shalukho.converter;

import by.shalukho.dto.UserDto;
import by.shalukho.entity.UserEntity;
import org.springframework.stereotype.Service;

@Service
public class UserConverter extends GenericConverterWithEnums<UserDto, UserEntity> {

    public UserConverter() {
        super(UserEntity.class);
    }

}
