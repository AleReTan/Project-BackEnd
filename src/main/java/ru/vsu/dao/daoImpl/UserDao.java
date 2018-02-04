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

    @Autowired
    public UserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void delete(UserEntity obj) {
        String sql = "DELETE  FROM  eav.users  WHERE eav.users.login = ?";
        jdbcTemplate.update(sql, obj.getLogin());
    }

    @Override
    public void insert(UserEntity obj) {
        String sql = " INSERT INTO  eav.users VALUES (?,?,?)";
        jdbcTemplate.update(sql, obj.getLogin(), obj.getPassword(), obj.getRole());
    }

    @Override
    public void update(UserEntity obj) {
        String sql = " UPDATE eav.users SET role = ? WHERE eav.users.login = ?";
        jdbcTemplate.update(sql, obj.getLogin());
    }

    @Override
    public List<UserEntity> getAll() {
        String sql = "SELECT * FROM  eav.users";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    public UserEntity getUserByLogin(String login) {
        String sql = "SELECT * FROM  eav.users  WHERE eav.users.login = ?";
        List<UserEntity> users = jdbcTemplate.query(sql, new UserMapper(), login);
        return users.get(0);
    }
}
