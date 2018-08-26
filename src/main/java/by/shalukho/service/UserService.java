package by.shalukho.service;

import by.shalukho.converter.UserConverter;
import by.shalukho.dto.UserDto;
import by.shalukho.entity.UserEntity;
import by.shalukho.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService extends AbstractService<UserDto, UserEntity> {

    public UserService(UserRepository userRepository, UserConverter userConverter) {
        super(userRepository, userConverter, UserDto.class, UserEntity.class);
    }

    public UserDto getUser(final String login) {
        return getConverter().convertToDto(((UserRepository) getRepository()).findByLogin(login).get());
    }

    @Override
    public Optional<UserEntity> findByActiveAndId(boolean active, Long id) {
        return ((UserRepository) getRepository()).findByActiveAndId(active, id);
    }

    @Override
    public List<UserEntity> findAllByActive(boolean active) {
        return ((UserRepository) getRepository()).findAllByActive(active);
    }
}
