package ru.vsu.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.dao.daoImpl.UserDao;
import ru.vsu.entity.DriverEntity;
import ru.vsu.entity.UserEntity;
import ru.vsu.services.serviceImpl.UserService;

import java.util.List;

/*
* Для того, чтобы убедиться в работе тестового контроллера необходимо проверить наличие
* в схеме  eav таблицы Users и пользователей в ней с полями
* login: , password: без использования криптования, role:{USER, ADMIN}
* При переходе  на вкладку ("/rest/user/**") требуется роль ADMIN
* а на вкладку ("/rest/help/**") роль ("USER")
*/

@RestController
public class UserRestController {
    private UserDao userRepository;
    private UserService userService;

    @Autowired
    public UserRestController(UserDao userRepository,UserService userService ) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @RequestMapping(value = "rest/user/admin", method = RequestMethod.GET)
    public UserEntity getUser(@RequestHeader("Authorization") String a) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userRepository.getUserByLogin(authentication.getName());
    }

    @RequestMapping(value = "rest/help/user", method = RequestMethod.GET)
    public UserEntity getHelp(@RequestHeader("Authorization") String a) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return userRepository.getUserByLogin(authentication.getName());
    }

    @RequestMapping(value = "/admin/users", method = RequestMethod.GET)
    public List<UserEntity> getAllUsers() {
        return userService.getAll();
    }

    // Код, для фронт части для кодирования токена
    /*String originalInput = "login:pass";
    String token = "Base " + Base64.getEncoder().encodeToString(originalInput.getBytes());*/
}

