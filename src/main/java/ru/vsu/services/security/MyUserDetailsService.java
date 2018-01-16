package ru.vsu.services.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.vsu.dao.daoImpl.UserDao;
import ru.vsu.entity.UserEntity;
import java.util.Arrays;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private UserDao userDao;

    @Autowired
    public MyUserDetailsService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        UserEntity userAuth = userDao.getUserByLogin(login);
        if (userAuth == null) {
            throw new UsernameNotFoundException("User by login " + login + " not found");
        }
        GrantedAuthority authority = new SimpleGrantedAuthority(userAuth.getRole());
        return new User(userAuth.getLogin(), userAuth.getPassword(), Arrays.asList(authority));
    }
}
