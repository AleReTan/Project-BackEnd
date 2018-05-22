package ru.vsu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.services.serviceImpl.DriverService;
import ru.vsu.services.serviceImpl.UserService;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private DriverService driverService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getRole(@RequestHeader("Authorization") String a) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String login = user.getUsername();
        String role = userService.getUserByLogin(login).getRole();

        if(role.equals("DRIVER")){
          return String.valueOf(driverService.getDriverIdByLogin(login));
        }
        return userService.getUserByLogin(user.getUsername()).getRole();
    }
}
