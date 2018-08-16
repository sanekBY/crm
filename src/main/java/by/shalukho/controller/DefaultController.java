package by.shalukho.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DefaultController {

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "/home";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin() {
        return "/admin/admin";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user() {
        return "/user/user";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about() {
        return "/about";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "/login";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String error403() {
        return "/error/403";
    }

}


