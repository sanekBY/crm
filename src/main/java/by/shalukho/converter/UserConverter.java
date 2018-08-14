package by.shalukho.converter;

import by.shalukho.dbo.UserEntity;
import by.shalukho.dto.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserConverter implements DtoDboConverter<UserDto, UserEntity> {
    @Override
    public UserDto convertToDto(final UserEntity dbo) {
        final UserDto userDto = new UserDto();
        BeanUtils.copyProperties(dbo, userDto);
        return userDto;
    }

    @Override
    public UserEntity convertToDbo(final UserDto dto) {
        final UserEntity userDbo = new UserEntity();
        BeanUtils.copyProperties(dto, userDbo);
        return userDbo;
    }
}
