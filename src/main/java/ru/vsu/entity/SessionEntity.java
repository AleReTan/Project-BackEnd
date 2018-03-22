package ru.vsu.entity;

import ru.vsu.annotation.ObjectTypeId;
import ru.vsu.annotation.ParamAttributeId;
import ru.vsu.annotation.Reference;

import java.util.Objects;


public class SessionEntity {

    public static String LOGIN = "login";
    public static String TIME_OF_BEGIN = "timeOfBegin";
    public static String TIME_RECENT_ACTIVITY = "timeRecentActivity";

    private String login;
    private String timeOfBegin;
    private String timeRecentActivity;

    public SessionEntity(String login, String timeOfBegin, String timeRecentActivity) {
        this.login = login;
        this.timeOfBegin = timeOfBegin;
        this.timeRecentActivity = timeRecentActivity;
    }

    public SessionEntity() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTimeOfBegin() {
        return timeOfBegin;
    }

    public void setTimeOfBegin(String timeOfBegin) {
        this.timeOfBegin = timeOfBegin;
    }

    public String getTimeRecentActivity() {
        return timeRecentActivity;
    }

    public void setTimeRecentActivity(String timeRecentActivity) {
        this.timeRecentActivity = timeRecentActivity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SessionEntity that = (SessionEntity) o;
        return Objects.equals(this.login, that.getLogin())&&
                Objects.equals(this.timeOfBegin, that.getTimeOfBegin()) &&
                Objects.equals(this.timeRecentActivity, that.getTimeRecentActivity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(),login, timeOfBegin, timeRecentActivity);
    }
}

