package ru.vsu.config.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import ru.vsu.entity.SessionEntity;
import ru.vsu.services.serviceImpl.SessionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * A logout handler which clears a defined list of cookies, using the context path as the
 * cookie path.
 *
 * @author Luke Taylor
 * @since 3.1
 */
public final class SessionClearingLogoutHandler implements LogoutHandler {

    private SessionService sessionService;

    @Autowired
    public SessionClearingLogoutHandler(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || !header.startsWith(SessionService.SESSION)) {
            return;
        }

        String authOption;
        if (header.startsWith(SessionService.BASE)) {
            authOption = SessionService.BASE;
        } else {
            authOption = SessionService.SESSION;
        }

        try {
            String[] tokens = sessionService.extractAndDecodeHeader(header, request, authOption);
            int tokensLength = tokens.length;

            if (tokensLength == 1) {
                String token = tokens[0];
                SessionEntity session = sessionService.getSessionById(Long.valueOf(token));
                if (session != null) {
                    sessionService.delete(session);
                    response.addHeader(HttpHeaders.AUTHORIZATION, sessionService.LOGOUT + token);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}