package by.shalukho.converter;

import by.shalukho.dto.UserDto;
import by.shalukho.entity.UserEntity;
import by.shalukho.enums.RoleEnum;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;

@Service
public class UserConverter extends GenericConverter<UserDto, UserEntity> {

    public UserConverter() {
        super(UserDto.class, UserEntity.class);
    }

    @Override
    protected BiFunction<UserDto, UserEntity, UserEntity> getDtoToEntityFunction() {
        BiFunction<UserDto, UserEntity, UserEntity> function = (dto, entity) -> {
            if (dto.getRole().equals("admin")) {
                entity.setRole(RoleEnum.ADMIN);
            } else {
                entity.setRole(RoleEnum.USER);
            }
            return entity;
        };
        return function;
    }
}
