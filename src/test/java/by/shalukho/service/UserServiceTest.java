package by.shalukho.service;

import by.shalukho.converter.UserConverter;
import by.shalukho.dto.UserDto;
import by.shalukho.entity.RoleEnum;
import by.shalukho.entity.UserEntity;
import by.shalukho.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    private static final Long USER_ID = 1L;
    private static final String FIRST_NAME = "Alex";
    private static final String LAST_NAME = "Alexov";
    private static final String LOGIN = "alex";
    private static final String PASSWORD = "password";

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Spy
    private UserConverter userConverter;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getUserTest() {
        final UserEntity userEntity = getUserEntity();

        Mockito.when(userService.findByActiveIsTrueAndId(USER_ID)).thenReturn(Optional.of(userEntity));

        final UserDto userDto = userService.findById(USER_ID);

        Assert.assertEquals(USER_ID, userDto.getId());
        Assert.assertEquals(FIRST_NAME, userDto.getFirstName());
        Assert.assertEquals(LAST_NAME, userDto.getLastName());
        Assert.assertEquals(LOGIN, userDto.getLogin());
        Assert.assertEquals(PASSWORD, userDto.getPassword());
        Assert.assertEquals(RoleEnum.ADMIN.toString(), userDto.getRole());
    }

    private UserEntity getUserEntity() {
        final UserEntity userEntity = new UserEntity();
        userEntity.setId(USER_ID);
        userEntity.setFirstName(FIRST_NAME);
        userEntity.setLastName(LAST_NAME);
        userEntity.setLogin(LOGIN);
        userEntity.setPassword(PASSWORD);
        userEntity.setRole(RoleEnum.ADMIN);
        return userEntity;
    }

    @Test
    public void saveUser() {
        userService.save(new UserDto());
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void deleteUser() {
        final UserEntity userEntity = getUserEntity();

        Mockito.when(userRepository.findById(USER_ID)).thenReturn(Optional.of(userEntity));

        userService.delete(USER_ID);

        Mockito.verify(userRepository, Mockito.times(1)).findById(Mockito.any());
        Mockito.verify(userRepository, Mockito.times(1)).save(Mockito.any());
    }

}
