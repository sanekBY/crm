package by.shalukho.controller;

import by.shalukho.dto.UserDto;
import by.shalukho.entity.UserEntity;
import by.shalukho.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/user")
public class UserController extends AbstractController<UserDto, UserEntity> {

    @Autowired
    public UserController(final UserService userService) {
        super(userService, UserDto.class);
    }

    @Override
    protected String getAttribute() {
        return "userDto";
    }

    @Override
    protected String getListAttribute() {
        return "users";
    }

    @Override
    protected String getListHtml() {
        return "/user/users";
    }

    @Override protected String getHtml() {
        return "/user/user";
    }
}
