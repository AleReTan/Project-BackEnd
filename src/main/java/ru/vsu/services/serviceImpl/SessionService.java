package ru.vsu.services.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.stereotype.Service;
import ru.vsu.dao.daoImpl.SessionDao;
import ru.vsu.dao.daoImpl.UserDao;
import ru.vsu.entity.SessionEntity;
import ru.vsu.services.MyService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SessionService implements MyService<SessionEntity> {

    public static final String BASE = "Base ";
    public static final String SESSION = "Session:";
    public static final String LOGOUT = "Logout:";

    private SessionDao sessionDao;

    private UserDao userRepository;

    @Autowired
    public SessionService(SessionDao sessionDao, UserDao userRepository) {
        this.sessionDao = sessionDao;
        this.userRepository = userRepository;
    }

    @Override
    public void delete(SessionEntity obj) {
        sessionDao.delete(obj);
    }

    public void delete(long id) {
        sessionDao.delete(id);
    }

    @Override
    public long insert(SessionEntity obj) {
        return sessionDao.insert(obj.getLogin());
    }

    public void insert(String login) {
        sessionDao.insert(login);
    }

    @Override
    public void update(SessionEntity obj) {
        sessionDao.update(obj.getId());
    }

    public void update(Long id) {
        sessionDao.update(id);
    }

    public void updateDateBegin(Long id){
        sessionDao.updateDateBegin(id);
    }

    @Override
    public List<SessionEntity> getAll() {
        return sessionDao.getAll();
    }

    public void deleteAllOutdated() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("I delete all outdated in: " + currentDateTime);
       // List<SessionEntity> sessionEntities = sessionDao.getAllOutsiding(currentDateTime.minusHours(24), currentDateTime.minusMinutes(20));
        //Тест
        List<SessionEntity> sessionEntities = sessionDao.getAllOutsiding(currentDateTime.minusHours(5), currentDateTime.minusMinutes(3));

        if (sessionEntities!= null) {
            for (SessionEntity sessionEntity : sessionEntities) {
                sessionDao.delete(sessionEntity);
            }
        }
    }

    public SessionEntity getSessionByUserLogin(String login) {
        return sessionDao.getSessionByUserLogin(login);
    }

    public SessionEntity getSessionById(long id) {
        return sessionDao.getSessionByID(id);
    }

    public static String[] extractAndDecodeHeader(String header, HttpServletRequest request, String authOption) throws IOException {

        byte[] base64Token = header.substring(authOption.length()).getBytes("UTF-8");
        byte[] decoded;

        try {
            decoded = Base64.decode(base64Token);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }

        String token = new String(decoded,"UTF-8");

        if (authOption.equals(BASE)) {
            int delim = token.indexOf(":");

            if (delim == -1) {
                throw new BadCredentialsException("Invalid basic authentication token");
            }
            return new String[]{token.substring(0, delim), token.substring(delim + 1)};
        } else {
            return new String[]{token};
        }
    }
}