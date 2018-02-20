package ru.vsu.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vsu.dao.daoImpl.SessionDao;
import ru.vsu.dao.daoImpl.UserDao;
import ru.vsu.entity.SessionEntity;
import ru.vsu.entity.UserEntity;
import ru.vsu.services.MyService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SessionService implements MyService<SessionEntity> {

    private SessionDao sessionDao;

    private UserDao userRepository;

    @Autowired
    public SessionService(SessionDao sessionDao, UserDao userRepository) {
        this.sessionDao = sessionDao;
        this.userRepository = userRepository;
    }

    @Override
    public void delete(SessionEntity obj) {
        String login = obj.getLogin();
        if (!sessionDao.isSessionNotExist(login)) {
            sessionDao.delete(obj);
        }
    }

    public void delete(String login) {
        if (!sessionDao.isSessionNotExist(login)) {
            sessionDao.delete(login);
        }
    }

    @Override
    public void insert(SessionEntity obj) {
            sessionDao.insert(obj.getLogin());
        }

    //  если пользователь авторизовался и для него нет сессии, то мы добавляем сессию
    public void insert(String login) {
        sessionDao.insert(login);
    }

    @Override
    public void update(SessionEntity obj) {
        sessionDao.update(obj.getLogin());
    }

    public void update(String login) {
        sessionDao.update(login);
    }

    @Override
    public List<SessionEntity> getAll() {
        return sessionDao.getAll();
    }

    public void deleteAllOutsiding() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        List<SessionEntity> sessionEntities = sessionDao.getAllOutsiding(currentDateTime.minusHours(24), currentDateTime.minusMinutes(20));
        if (!sessionEntities.isEmpty()) {
            for (SessionEntity sessionEntity : sessionEntities) {
                sessionDao.delete(sessionEntity);
            }
        }

    }

    public SessionEntity getSessionByUserLogin (String login){
        return sessionDao.getSessionByUserLogin(login);
    }

    public void updateSession(String login) {
        if (getSessionByUserLogin(login) == null) {
            insert(login);
        } else {
            update(login);
        }
    }
}
