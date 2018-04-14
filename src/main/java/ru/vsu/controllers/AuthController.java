package ru.vsu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import ru.vsu.entity.CarEntity;
import ru.vsu.entity.DriverEntity;
import ru.vsu.entity.UserEntity;
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
