package ru.vsu.entity.mappers;

import ru.vsu.entity.ObjectEntity;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ObjectMapper implements RowMapper<ObjectEntity> {
    @Override
    public ObjectEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        return new ObjectEntity(resultSet.getBigDecimal("id").toBigInteger(),
                resultSet.getString("name"),
                resultSet.getBigDecimal("type_id").toBigInteger());
    }
}
