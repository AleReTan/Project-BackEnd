package ru.vsu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.vsu.dao.daoImpl.UserDao;
import ru.vsu.entity.DriverEntity;
import ru.vsu.entity.UserEntity;
import ru.vsu.services.serviceImpl.SessionService;
import ru.vsu.services.serviceImpl.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/*
* Для того, чтобы убедиться в работе тестового контроллера необходимо проверить наличие
* в схеме  eav таблицы Users и пользователей в ней с полями
* login: , password: без использования криптования, role:{USER, ADMIN}
* для добавления криптования необходимо перейти в класс Security config
*/

@RestController
public class UserRestController {
    private SessionService sessionService;
    private UserService userService;


    @Autowired
    public UserRestController(SessionService sessionService,UserService userService ) {
        this.sessionService = sessionService;
        this.userService = userService;
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public List<UserEntity> getAllUsers(@RequestHeader("Authorization") String a) {
        return userService.getAll();
    }


    @RequestMapping(value = "/admin/users/{login}", method = RequestMethod.GET)
    public UserEntity getUserByLogin(@PathVariable("login") String login,@RequestHeader("Authorization") String a) {
        return userService.getUserByLogin(login);
    }


    @RequestMapping(value = "/admin/users", method = RequestMethod.POST)
    public void createUser(@RequestBody UserEntity u, @RequestHeader("Authorization") String a) {
        userService.insert(u);
    }
    //удаление по логину
    @RequestMapping(value = "/admin/users/{login}", method = RequestMethod.DELETE)
    public void deleteUser(@RequestBody UserEntity u,@RequestHeader("Authorization") String a) {
        userService.delete(u);
    }

}