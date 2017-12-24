package ru.vsu.entity.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.vsu.entity.ParamsEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParamsMapper implements RowMapper<ParamsEntity> {
    @Override
    public ParamsEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        return new ParamsEntity(resultSet.getBigDecimal("id").toBigInteger(),
                resultSet.getBigDecimal("attr_id").toBigInteger(),
                resultSet.getBigDecimal("object_id").toBigInteger(),
                resultSet.getString("value"));
    }


}
