package by.shalukho.service;

import by.shalukho.converter.UserConverter;
import by.shalukho.dto.UserDto;
import by.shalukho.entity.UserEntity;
import by.shalukho.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<UserDto, UserEntity, UserRepository> {

    public UserService(UserRepository userRepository, UserConverter userConverter) {
        super(userRepository, userConverter);
    }

    public UserDto getUser(final String login) {
        return getConverter().convertToDto(getRepository().findByLogin(login).get());
    }
}
