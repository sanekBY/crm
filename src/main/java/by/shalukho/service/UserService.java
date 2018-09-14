package by.shalukho.service;

import by.shalukho.converter.UserConverter;
import by.shalukho.dto.UserDto;
import by.shalukho.entity.UserEntity;
import by.shalukho.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService extends AbstractService<UserDto, UserEntity, UserRepository> {

    public UserService(UserRepository userRepository, UserConverter userConverter) {
        super(userRepository, userConverter);
    }

    public UserDto getUser(final String login) {
        return getConverter().convertToDto(getRepository().findByLogin(login).get());
    }

    @Override
    public Optional<UserEntity> findByActiveAndId(boolean active, Long id) {
        return getRepository().findByActiveAndId(active, id);
    }

    @Override
    public List<UserEntity> findAllByActive(boolean active) {
        return getRepository().findAllByActive(active);
    }
}
