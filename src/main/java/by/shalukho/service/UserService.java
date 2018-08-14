package by.shalukho.service;

import by.shalukho.converter.GenericConverter;
import by.shalukho.dbo.UserEntity;
import by.shalukho.dto.UserDto;
import by.shalukho.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final GenericConverter<UserDto, UserEntity> genericConverter;

    @Autowired
    public UserService(UserRepository userRepository, GenericConverter genericConverter) {
        this.userRepository = userRepository;
        this.genericConverter = genericConverter;
        this.genericConverter.setDtoClazz(UserDto.class);
        this.genericConverter.setDboClazz(UserEntity.class);
    }

    public void createUser(final UserDto userDto) {
        userRepository.save(genericConverter.convertToDbo(userDto));
    }

    public UserDto getUser(final Long id) {
        return genericConverter.convertToDto(userRepository.getOne(id));
    }

    public UserDto getUser(final String login) {
        return genericConverter.convertToDto(userRepository.findByLogin(login));
    }
}
