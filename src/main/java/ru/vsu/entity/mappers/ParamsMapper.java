package ru.vsu.entity.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.vsu.entity.ParamsEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ParamsMapper implements RowMapper<ParamsEntity> {
    @Override
    public ParamsEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        return new ParamsEntity(resultSet.getLong("id"),
                resultSet.getLong("attr_id"),
                resultSet.getLong("object_id"),
                resultSet.getString("value"));
    }


}
