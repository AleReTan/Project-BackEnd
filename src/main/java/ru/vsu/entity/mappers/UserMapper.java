package ru.vsu.entity.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.vsu.entity.UserEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserEntity> {
    @Override
    public UserEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        return new UserEntity(resultSet.getString(UserEntity.LOGIN), resultSet.getString(UserEntity.PASSWORD), resultSet.getString(UserEntity.ROLE));
    }
}