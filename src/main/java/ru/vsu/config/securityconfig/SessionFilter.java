package ru.vsu.config.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import ru.vsu.services.serviceImpl.SessionService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

// этот фильтр стоит после фильтра аутентификации, соответственно, мы точно уверены, что пользователь прошел аутентификацию.
//В связи с этим мы должны проверить, назначена ли сессия пользователю, если нет - добавить сессию, если да - обновить дату последней активности
public class SessionFilter extends GenericFilterBean {
    private SessionService sessionService;

    @Autowired
    public SessionFilter(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Получили логин пользователя и обновили сессию
        sessionService.updateSession(SecurityContextHolder.getContext().getAuthentication().getName());
        chain.doFilter(request, response);
    }
}
