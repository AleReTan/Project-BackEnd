package ru.vsu.entity.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.vsu.entity.ObjectTypeEntity;


import java.sql.ResultSet;
import java.sql.SQLException;

public class ObjectTypeMapper implements RowMapper<ObjectTypeEntity> {
    @Override
    public ObjectTypeEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        return new ObjectTypeEntity(resultSet.getBigDecimal("id").toBigInteger(),
                resultSet.getString("name"));
    }
}
