package by.shalukho.service;

import by.shalukho.converter.GenericConverter;
import by.shalukho.dbo.UserEntity;
import by.shalukho.dto.UserDto;
import by.shalukho.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final GenericConverter<UserDto, UserEntity> genericConverter;

    public void createUser(final UserDto userDto) {
        userRepository.save(genericConverter.getConverter(UserDto.class, UserEntity.class).convertToDbo(userDto));
    }

    public UserDto getUser(final Long id) {
        return genericConverter.getConverter(UserDto.class, UserEntity.class).convertToDto(userRepository.getOne(id));
    }

    public UserDto getUser(final String login) {
        return genericConverter.getConverter(UserDto.class, UserEntity.class).convertToDto(userRepository.findByLogin(login));
    }
}
