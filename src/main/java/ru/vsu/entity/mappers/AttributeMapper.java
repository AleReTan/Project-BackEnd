package ru.vsu.entity.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.vsu.entity.AttributeEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AttributeMapper implements RowMapper<AttributeEntity> {
    @Override
    public AttributeEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        return new AttributeEntity(resultSet.getBigDecimal("id").toBigInteger(),
                resultSet.getString("name"),
                resultSet.getBigDecimal("object_type_id").toBigInteger(),
                resultSet.getString("value_type"),
                resultSet.getBoolean("require"));

    }

}
