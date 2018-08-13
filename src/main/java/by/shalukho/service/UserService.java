package by.shalukho.service;

import by.shalukho.dbo.UserDbo;
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

    public void createUser(final UserDbo userDto) {
        userRepository.save(userDto);
    }

    public UserDbo getUser(final Long id) {
        return userRepository.getOne(id);
    }
}
