package ru.vsu.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.vsu.dao.Dao;
import ru.vsu.entity.SessionEntity;
import ru.vsu.entity.mappers.SessionMapper;
import ru.vsu.entity.mappers.UserMapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class SessionDao implements Dao<SessionEntity> {

    private final JdbcTemplate jdbcTemplate;
    private static final String DELETE_SESSION = "DELETE  FROM  eav.sessions  WHERE eav.sessions.login = ?";
    private static final String ADD_SESSION = " INSERT INTO  eav.sessions   VALUES (DEFAULT ,?,?,?)";
    private static final String UPDATE_SESSION = " UPDATE eav.sessions    SET timerecentactivity = ? WHERE eav.sessions.login = ?";
    private static final String GET_ALL_SESSION = "SELECT * FROM  eav.sessions";
    private static final String GET_SESSION_BY_LOGIN = "SELECT * FROM  eav.sessions   WHERE eav.sessions.login = ?";
    private static final String GET_ALL_OUTSIDING = "SELECT * FROM  eav.sessions  WHERE eav.sessions.timeofbegin < ? AND eav.sessions.timerecentactivity < ? ";
    private static final String DELETE_ALL_OUTSIDING = "DELETE  FROM eav.sessions  WHERE eav.sessions.timeofbegin < ?";

    @Autowired
    public SessionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void delete(String login) {
        jdbcTemplate.update(DELETE_SESSION, login);
    }

    @Override
    public void delete(SessionEntity obj) {
        jdbcTemplate.update(DELETE_SESSION, obj.getLogin());
    }

    @Override
    public void insert(SessionEntity obj) {
        jdbcTemplate.update(ADD_SESSION, obj.getLogin(), obj.getTimeOfBegin(),obj.getTimeRecentActivity());
    }

    public void insert(String login) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        jdbcTemplate.update(ADD_SESSION,login, currentDateTime, currentDateTime);
    }

    @Override
    public void update(SessionEntity obj) {
        jdbcTemplate.update(UPDATE_SESSION, LocalDateTime.now(),obj.getLogin());
    }

    public void update(String login) {
        jdbcTemplate.update(UPDATE_SESSION, LocalDateTime.now(),login);
    }

    @Override
    public List<SessionEntity> getAll() {
        return jdbcTemplate.query(GET_ALL_SESSION, new SessionMapper());
    }

    public SessionEntity getSessionByUserLogin(String login) {
        //Здесь может вылететь эксцепшен array of bounfds
        List<SessionEntity> users = jdbcTemplate.query(GET_SESSION_BY_LOGIN, new SessionMapper(), login);
        return users.get(0);
    }

    public boolean isSessionNotExist(String login) {
        //Здесь может вылететь эксцепшен array of bounfds
        List<SessionEntity> users = jdbcTemplate.query(GET_SESSION_BY_LOGIN, new SessionMapper(), login);
        return users.isEmpty();
    }

    public List<SessionEntity> getAllOutsiding(LocalDateTime timeofbegin, LocalDateTime timerecentactivity) {
        return jdbcTemplate.query(GET_ALL_OUTSIDING, new SessionMapper());
    }

    public void deleteAllOutsiding(LocalDateTime timeofbegin) {
        jdbcTemplate.update(DELETE_ALL_OUTSIDING,timeofbegin);
    }
}
