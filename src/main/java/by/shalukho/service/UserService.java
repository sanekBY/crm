package by.shalukho.service;

import by.shalukho.converter.UserConverter;
import by.shalukho.dbo.UserEntity;
import by.shalukho.dto.UserDto;
import by.shalukho.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<UserDto, UserEntity> {

    public UserService(UserRepository userRepository, UserConverter userConverter) {
        super(userRepository, userConverter, UserDto.class, UserEntity.class);
    }

    public UserDto getUser(final String id) {
        return getConverter().convertToDto(((UserRepository) getRepository()).findByLogin(id));
    }

}
