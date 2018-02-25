package ru.vsu.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.dao.daoImpl.UserDao;
import ru.vsu.entity.UserEntity;
import ru.vsu.services.MyService;

import java.util.List;

@Service
public class UserService implements MyService<UserEntity> {

    private UserDao userDao;

    @Autowired
    public UserService(UserDao userDao){
      this.userDao =  userDao;
    }

    //@Override
    public void delete(String login) {
        userDao.delete(login);
    }

    @Override
    public void delete(UserEntity obj) {
        userDao.delete(obj);
    }

    @Override
    public void insert(UserEntity obj) {
        userDao.insert(obj);
    }

    @Override
    public void update(UserEntity obj) {
        userDao.update(obj);
    }

    @Override
    public List<UserEntity> getAll() {
        return userDao.getAll();
    }

    public UserEntity getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

}
