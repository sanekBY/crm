package by.shalukho.converter;

import by.shalukho.entity.UserEntity;
import by.shalukho.dto.UserDto;
import by.shalukho.enums.RoleEnum;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;

@Service
public class UserConverter extends GenericConverter<UserDto, UserEntity> {

    @Override
    protected BiFunction<UserDto, UserEntity, UserEntity> getEntityToDtoFunction() {
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
