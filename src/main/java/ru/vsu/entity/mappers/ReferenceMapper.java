package ru.vsu.entity.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.vsu.entity.ReferenceEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReferenceMapper implements RowMapper<ReferenceEntity> {
    @Override
    public ReferenceEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        return new ReferenceEntity(resultSet.getBigDecimal("reference").toBigInteger(),
                resultSet.getBigDecimal("object_id").toBigInteger(),
                resultSet.getBigDecimal("attr_id").toBigInteger());
    }
}
