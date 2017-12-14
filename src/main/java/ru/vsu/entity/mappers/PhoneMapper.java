package ru.vsu.entity.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.vsu.entity.Phone;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PhoneMapper implements RowMapper<Phone> {

    @Override
    public Phone mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Phone(resultSet.getInt("id"), resultSet.getString("model"),resultSet.getInt("price"));
    }
}
