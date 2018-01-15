package ru.vsu.entity.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.vsu.entity.ObjectEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ObjectMapper implements RowMapper<ObjectEntity> {
    @Override
    public ObjectEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        return new ObjectEntity(resultSet.getLong("id"),
                resultSet.getString("name"),
                resultSet.getLong("object_type_id"));
    }
}
