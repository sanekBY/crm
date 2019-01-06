package by.shalukho.service;

import by.shalukho.converter.UserConverter;
import by.shalukho.dto.UserDto;
import by.shalukho.entity.UserEntity;
import by.shalukho.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Optional<UserEntity> findByActiveIsTrueAndId(final Long id) {
        return getRepository().findByActiveIsTrueAndId(id);
    }

    @Override
    public List<UserEntity> findAllByActiveIsTrue() {
        return getRepository().findAllByActiveIsTrue();
    }

    @Override public Page<UserEntity> findAllByActiveIsTrue(final Pageable pageable) {
        return getRepository().findAllByActiveIsTrue(pageable);
    }
}
