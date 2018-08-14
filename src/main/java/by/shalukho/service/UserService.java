package by.shalukho.service;

import by.shalukho.dbo.UserEntity;
import by.shalukho.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void createUser(final UserEntity userDto) {
        userRepository.save(userDto);
    }

    public UserEntity getUser(final Long id) {
        return userRepository.getOne(id);
    }

    public UserEntity getUser(final String login) {
        return userRepository.findByLogin(login);
    }
}
