package by.shalukho.controller;

import by.shalukho.dto.UserDto;
import by.shalukho.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/api/user", method = RequestMethod.POST)
    public String createUser(@RequestBody final UserDto userDto) {
        userService.save(userDto);
        return "PersonEntity created";
    }

    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.GET)
    public UserDto getUser(@PathVariable("id") final Long id) {
        return userService.findById(id);
    }

    @RequestMapping(value = "/api/user/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable("id") final Long id) {
        userService.delete(id);
        return "User deleted";
    }

}
