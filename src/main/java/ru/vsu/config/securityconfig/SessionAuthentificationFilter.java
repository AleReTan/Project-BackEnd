package ru.vsu.config.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;
import ru.vsu.entity.SessionEntity;
import ru.vsu.entity.UserEntity;
import ru.vsu.services.serviceImpl.SessionService;
import ru.vsu.services.serviceImpl.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SessionAuthentificationFilter extends GenericFilterBean {
    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    private AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource = new WebAuthenticationDetailsSource();
    private AuthenticationEntryPoint authenticationEntryPoint;
    private AuthenticationManager authenticationManager;
    private boolean ignoreFailure = false;

    public SessionAuthentificationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        ignoreFailure = true;
    }

    public SessionAuthentificationFilter(AuthenticationManager authenticationManager,
                                         AuthenticationEntryPoint authenticationEntryPoint, SessionService sessionService, UserService userService) {
        this.sessionService = sessionService;
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }
    //~ Methods ========================================================================================================

    @Override
    public void afterPropertiesSet() {
        Assert.notNull(this.authenticationManager, "An AuthenticationManager is required");

        if (!isIgnoreFailure()) {
            Assert.notNull(this.authenticationEntryPoint, "An AuthenticationEntryPoint is required");
        }
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        final boolean debug = logger.isDebugEnabled();
        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || (!header.startsWith(SessionService.BASE) && !header.startsWith(SessionService.SESSION))) {
            chain.doFilter(request, response);
            return;
        }

        String authOption;
        if (header.startsWith(SessionService.BASE)) {
            authOption = SessionService.BASE;
        } else {
            authOption = SessionService.SESSION;
        }
        try {
            //Извлекаем токен
            String[] tokens = SessionService.extractAndDecodeHeader(header, request, authOption);
            int tokensLength = tokens.length;

            assert (tokensLength == 2 || tokensLength == 1);
            String username = " ";
            String password = " ";
            //если в токене логин и пароль
            if (tokensLength == 2) {
                username = tokens[0];
                password = tokens[1];
                //если пришел id сессии в токене
            } else if (tokensLength == 1) {
                SessionEntity session = sessionService.getSessionById(Long.valueOf(tokens[0]));
                username = (session == null) ? "" : session.getLogin();
                UserEntity user = userService.getUserByLogin(username);
                password = (user == null) ? "" : user.getPassword();
            }
            //Authentification
            if (authenticationIsRequired(username)) {
                UsernamePasswordAuthenticationToken authRequest =
                        new UsernamePasswordAuthenticationToken(username, password);
                authRequest.setDetails(authenticationDetailsSource.buildDetails(request));

                Authentication authResult = authenticationManager.authenticate(authRequest);
                System.out.println("До установления контекста" + request.getRequestURI());
                SecurityContextHolder.getContext().setAuthentication(authResult);
                onSuccessfulAuthentication(request, response, authResult, username, authOption);
            }

        } catch (AuthenticationException failed) {
            SecurityContextHolder.clearContext();

            onUnsuccessfulAuthentication(request, response, failed);

            if (ignoreFailure) {
                chain.doFilter(request, response);
            } else {
                authenticationEntryPoint.commence(request, response, failed);
            }
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean authenticationIsRequired(String username) {

        Authentication existingAuth = SecurityContextHolder.getContext().getAuthentication();

        if (existingAuth == null || !existingAuth.isAuthenticated()) {
            return true;
        }

        if (existingAuth instanceof UsernamePasswordAuthenticationToken && !existingAuth.getName().equals(username)) {
            return true;
        }

        if (existingAuth instanceof AnonymousAuthenticationToken) {
            return true;
        }

        return false;
    }

    protected void onSuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              Authentication authResult, String username, String authOption) throws IOException {

        //////////////////////Updating session////////////////////////////////////////////
        //тут надо подумать, чтобы ексцепшн не вылетел
        SessionEntity userSession = sessionService.getSessionByUserLogin(username);

        if (authOption.equals(SessionService.BASE)) {

            if (userSession != null) {
                sessionService.delete(userSession);
                sessionService.insert(username);
                userSession = sessionService.getSessionByUserLogin(username);
                //sessionService.updateDateBegin(userSession.getId());
            } else {
                sessionService.insert(username);
                userSession = sessionService.getSessionByUserLogin(username);
            }
        } else {
            if (userSession != null) {
                sessionService.update(userSession.getId());
            }
        }
        ////////////////////////дабавляем заголовок, содержащий id сессии//////////////
        String originalInput = Long.toString(userSession.getId());
        String token = SessionService.SESSION + java.util.Base64.getEncoder().encodeToString(originalInput.getBytes());
        response.addHeader(HttpHeaders.AUTHORIZATION, token);
        System.out.println(request.getRequestURI());

    }

    protected void onUnsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                                AuthenticationException failed) throws IOException {
    }

    protected AuthenticationEntryPoint getAuthenticationEntryPoint() {
        return authenticationEntryPoint;
    }

    protected AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    protected boolean isIgnoreFailure() {
        return ignoreFailure;
    }

    public void setAuthenticationDetailsSource(AuthenticationDetailsSource<HttpServletRequest, ?> authenticationDetailsSource) {
        Assert.notNull(authenticationDetailsSource, "AuthenticationDetailsSource required");
        this.authenticationDetailsSource = authenticationDetailsSource;
    }

}
