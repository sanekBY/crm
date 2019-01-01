package by.shalukho.controller;

import by.shalukho.dto.UserDto;
import by.shalukho.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = UserController.CURRENT_URL)
public class UserController extends AbstractController<UserDto, UserService> {

    public static final String USER_DTO_ATTRIBUTE = "userDto";
    public static final String CURRENT_URL = "/user";

    @Autowired
    public UserController(final UserService userService) {
        super(userService, UserDto.class);
    }

    @Override
    protected String getAttribute() {
        return USER_DTO_ATTRIBUTE;
    }

    @Override
    protected String getListAttribute() {
        return "users";
    }

    @Override
    protected String getListHtml() {
        return "user/users";
    }

    @Override protected String getHtml() {
        return "user/user";
    }

    @Override protected String getCurrentUrl() {
        return CURRENT_URL;
    }
}
