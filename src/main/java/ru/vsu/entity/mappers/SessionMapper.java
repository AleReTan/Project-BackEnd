package ru.vsu.entity.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.vsu.entity.SessionEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class SessionMapper implements RowMapper<SessionEntity> {
    @Override
    public SessionEntity mapRow(ResultSet resultSet, int i) throws SQLException {

        return new SessionEntity(resultSet.getLong(SessionEntity.ID_SESSION),resultSet.getString(SessionEntity.LOGIN), resultSet.getObject(SessionEntity.TIME_OF_BEGIN, LocalDateTime.class), resultSet.getObject(SessionEntity.TIME_RECENT_ACTIVITY, LocalDateTime.class));
    }
    //https://jdbc.postgresql.org/documentation/head/8-date-time.html
}