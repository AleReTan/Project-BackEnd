package ru.vsu.entity;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

@Component
public class SessionEntity {
    public static String ID_SESSION = "id";
    public static String LOGIN = "login";
    public static String TIME_OF_BEGIN = "timeOfBegin";
    public static String TIME_RECENT_ACTIVITY = "timeRecentActivity";

    private long id;
    private String login;
    private LocalDateTime timeOfBegin;
    private LocalDateTime timeRecentActivity;

    public SessionEntity(long id,String login, LocalDateTime timeOfBegin, LocalDateTime timeRecentActivity) {
        this.id = id;
        this.login = login;
        this.timeOfBegin = timeOfBegin;
        this.timeRecentActivity = timeRecentActivity;
    }

    public SessionEntity() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public LocalDateTime getTimeOfBegin() {
        return timeOfBegin;
    }

    public void setTimeOfBegin(LocalDateTime timeOfBegin) {
        this.timeOfBegin = timeOfBegin;
    }

    public LocalDateTime getTimeRecentActivity() {
        return timeRecentActivity;
    }

    public void setTimeRecentActivity(LocalDateTime timeRecentActivity) {
        this.timeRecentActivity = timeRecentActivity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SessionEntity that = (SessionEntity) o;
        return  Objects.equals(this.id, that.getId()) &&
                Objects.equals(this.login, that.getLogin())&&
                Objects.equals(this.timeOfBegin, that.getTimeOfBegin()) &&
                Objects.equals(this.timeRecentActivity, that.getTimeRecentActivity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),id, login, timeOfBegin, timeRecentActivity);
    }


}

