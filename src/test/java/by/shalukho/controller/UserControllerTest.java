package by.shalukho.controller;


import by.shalukho.dto.UserDto;
import by.shalukho.entity.RoleEnum;
import by.shalukho.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserControllerTest extends AbstractControllerTest {

    private static final String USER_WITHOUT_ID_URL = "/user";
    private static final String LAST_NAME = "Alexandeov";
    private static final String FIRST_NAME = "Alex";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "1234";

    @Autowired
    private UserService userService;

    @Test
    public void checkUserCreation() {
        UserDto userDto = createUser(2L);
        checkEntityCreation(USER_WITHOUT_ID_URL, UserController.USER_DTO_ATTRIBUTE, userDto);
    }

    @Test(expected = RuntimeException.class)
    public void checkUserRemoving() {
        UserDto userDto = createUser(3L);
        Assert.assertEquals(userService.findById(userDto.getId()), userDto);
        deleteRequest(USER_WITHOUT_ID_URL, userDto.getId());
        userService.findById(userDto.getId());
    }

    private UserDto createUser(final Long id) {
        UserDto userDto = new UserDto();
        userDto.setId(id);
        userDto.setPassword(PASSWORD);
        userDto.setLogin(LOGIN);
        userDto.setLastName(LAST_NAME);
        userDto.setFirstName(FIRST_NAME);
        userDto.setRole(RoleEnum.ADMIN.toString());
        return userDto;
    }

}
