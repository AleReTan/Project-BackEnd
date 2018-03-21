package ru.vsu.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.vsu.dao.Dao;
import ru.vsu.entity.UserEntity;
import ru.vsu.entity.mappers.UserMapper;

import java.util.List;

@Repository
public class UserDao implements Dao<UserEntity> {

    private final JdbcTemplate jdbcTemplate;
    private static final String DELETE_USER = "DELETE  FROM  eav.users  WHERE eav.users.login = ?";
    private static final String ADD_USER = " INSERT INTO  eav.users VALUES (?,?,?)";
    private static final String UPDATE_USER = " UPDATE eav.users SET role = ? WHERE eav.users.login = ?";
    private static final String GET_ALL_USERS = "SELECT * FROM  eav.users";
    private static final String GET_USER_BY_LOGIN = "SELECT * FROM  eav.users  WHERE eav.users.login = ?";

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void delete(String login) {
        jdbcTemplate.update(DELETE_USER, login);
    }

    @Override
    public void delete(UserEntity obj) {
        // не переопределяем пока этот метод
    }

    @Override
    public void insert(UserEntity obj) {
        jdbcTemplate.update(ADD_USER, obj.getLogin(), obj.getPassword(), obj.getRole());
    }

    @Override
    public void update(UserEntity obj) {
        jdbcTemplate.update(UPDATE_USER, obj.getLogin());
    }

    @Override
    public List<UserEntity> getAll() {
        return jdbcTemplate.query(GET_ALL_USERS, new UserMapper());
    }

    public UserEntity getUserByLogin(String login) {
        List<UserEntity> users = jdbcTemplate.query(GET_USER_BY_LOGIN, new UserMapper(), login);
        return (users == null || users.isEmpty()) ? null : users.get(0);
    }

    public boolean isUserExist(String login) {
        List<UserEntity> users = jdbcTemplate.query(GET_USER_BY_LOGIN, new UserMapper(), login);
        return !users.isEmpty();
    }
}
