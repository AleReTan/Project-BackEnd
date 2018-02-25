package ru.vsu.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.vsu.dao.Dao;
import ru.vsu.entity.SessionEntity;
import ru.vsu.entity.mappers.SessionMapper;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class SessionDao implements Dao<SessionEntity> {

    private final JdbcTemplate jdbcTemplate;

    private static final String DELETE_SESSION_BY_ID = "DELETE  FROM eav.sessions  WHERE eav.sessions.id = ?";

    private static final String ADD_SESSION = " INSERT INTO  eav.sessions   VALUES (DEFAULT ,?,?,?)";

    private static final String UPDATE_SESSION_BY_ID = " UPDATE eav.sessions    SET timerecentactivity = ? WHERE eav.sessions.id = ?";

    private static final String GET_ALL_SESSION = "SELECT * FROM  eav.sessions";

    private static final String GET_SESSION_BY_LOGIN = "SELECT * FROM  eav.sessions   WHERE eav.sessions.login = ?";
    private static final String GET_SESSION_BY_ID = "SELECT * FROM  eav.sessions   WHERE eav.sessions.id = ?";

    private static final String GET_ALL_OUTSIDING = "SELECT * FROM  eav.sessions  WHERE eav.sessions.timeofbegin < ? AND eav.sessions.timerecentactivity < ? ";
    private static final String DELETE_ALL_OUTSIDING = "DELETE  FROM eav.sessions  WHERE eav.sessions.timeofbegin < ?";


    @Autowired
    public SessionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void delete(SessionEntity obj) {
        jdbcTemplate.update(DELETE_SESSION_BY_ID, obj.getId());
    }

    public void delete(long id) {
        jdbcTemplate.update(DELETE_SESSION_BY_ID,id);
    }

    @Override
    public void insert(SessionEntity obj) {
        jdbcTemplate.update(ADD_SESSION, obj.getLogin(), obj.getTimeOfBegin(),obj.getTimeRecentActivity());
    }

    public void insert(String login) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        jdbcTemplate.update(ADD_SESSION,login, currentDateTime,currentDateTime);
    }

    @Override
    public void update(SessionEntity obj) {
        jdbcTemplate.update(UPDATE_SESSION_BY_ID, LocalDateTime.now(),obj.getId());
    }

    public void update(long id) {
        jdbcTemplate.update(UPDATE_SESSION_BY_ID, LocalDateTime.now(),id);
    }
    @Override
    public List<SessionEntity> getAll() {
        List<SessionEntity> sessions =  jdbcTemplate.query(GET_ALL_SESSION, new SessionMapper());
        return sessions == null || sessions.isEmpty() ? null : sessions;
    }

    public SessionEntity getSessionByUserLogin(String login) {
        List<SessionEntity> sessions = jdbcTemplate.query(GET_SESSION_BY_LOGIN, new SessionMapper(), login);
        return sessions == null || sessions.isEmpty() ? null : sessions.get(0);
    }

    public SessionEntity getSessionByID(long id) {
        List<SessionEntity> sessions = jdbcTemplate.query(GET_SESSION_BY_ID, new SessionMapper(), id);
        return sessions == null || sessions.isEmpty() ? null : sessions.get(0);
    }

    public boolean isSessionExist(long id) {
        List<SessionEntity> sessions = jdbcTemplate.query(GET_SESSION_BY_ID, new SessionMapper(), id);
        return sessions == null || sessions.isEmpty() ? false : true;
    }

    public List<SessionEntity> getAllOutsiding(LocalDateTime timeOfBegin, LocalDateTime timeRecentActivity) {
         List<SessionEntity> sessions =  jdbcTemplate.query(GET_ALL_OUTSIDING, new SessionMapper(),timeOfBegin,timeRecentActivity);
        return sessions == null || sessions.isEmpty() ? null : sessions;
    }

    public void deleteAllOutsiding(LocalDateTime timeOfBegin) {
        jdbcTemplate.update(DELETE_ALL_OUTSIDING, timeOfBegin);
    }
}