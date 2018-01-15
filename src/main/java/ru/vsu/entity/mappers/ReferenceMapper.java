package ru.vsu.entity.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.vsu.entity.ReferenceEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReferenceMapper implements RowMapper<ReferenceEntity> {
    @Override
    public ReferenceEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        return new ReferenceEntity(resultSet.getLong("reference"),
                resultSet.getLong("object_id"),
                resultSet.getLong("attr_id"));
    }
}
