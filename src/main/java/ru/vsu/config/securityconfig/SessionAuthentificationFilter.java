package ru.vsu.config.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.Assert;
import org.springframework.web.filter.GenericFilterBean;
import ru.vsu.entity.SessionEntity;
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
    private String credentialsCharset = "UTF-8";

    private static final String BASE = "Base ";
    private static final String SESSION = "Session:";

    public SessionAuthentificationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        ignoreFailure = true;
    }

    public SessionAuthentificationFilter(AuthenticationManager authenticationManager,
                                         AuthenticationEntryPoint authenticationEntryPoint,SessionService sessionService,UserService userService) {
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
        SessionEntity  userSession;

        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (header == null || (!header.startsWith(BASE) && !header.startsWith(SESSION))) {
            chain.doFilter(request, response);
            return;
        }


        String authOption;
        if (header.startsWith(BASE)) {
            authOption = BASE;
        } else {
            authOption = SESSION;
        }

        try {

            String[] tokens = extractAndDecodeHeader(header, request, authOption);
            int tokensLength = tokens.length;

            assert (tokensLength == 2 || tokensLength == 1);
            String username = " ";
            String password = " ";

            if (tokensLength == 2) {
                username = tokens[0];
                password = tokens[1];

            } else if (tokensLength == 1) {

                username = sessionService.getSessionById(Long.valueOf(tokens[0])).getLogin();
                password = userService.getUserByLogin(username).getPassword();
            }

            if (debug) {
                logger.debug("Basic Authentication Authorization header found for user '" + username + "'");
            }
//////////////////////////////Authentification//////////////////////////////////////////
            if (authenticationIsRequired(username)) {
                UsernamePasswordAuthenticationToken authRequest =
                        new UsernamePasswordAuthenticationToken(username, password);
                authRequest.setDetails(authenticationDetailsSource.buildDetails(request));

                Authentication authResult = authenticationManager.authenticate(authRequest);

                if (debug) {
                    logger.debug("Authentication success: " + authResult);
                }

                SecurityContextHolder.getContext().setAuthentication(authResult);

//////////////////////Updating session////////////////////////////////////////////

               if(authOption.equals(BASE)){
                   sessionService.insert(username);
                   userSession = sessionService.getSessionByUserLogin(username);
               }else{
                   userSession = sessionService.getSessionByUserLogin(username);
                   sessionService.update(userSession.getId());
               }
               //дабавляем заголовок, содержащий id сессии
                String originalInput = Long.toString(userSession.getId());
                String token = SESSION + java.util.Base64.getEncoder().encodeToString(originalInput.getBytes());
                response.addHeader(HttpHeaders.AUTHORIZATION,token);
                System.out.println(response.getHeader(HttpHeaders.AUTHORIZATION));
                //String role=userService.getUserByLogin(username).getRole();
                /////////////////////////////////////////////////////////
                //response.addHeader("ROLE", role);
                onSuccessfulAuthentication(request, response, authResult);
            }

        } catch (AuthenticationException failed) {
            SecurityContextHolder.clearContext();

            if (debug) {
                logger.debug("Authentication request for failed: " + failed);
            }

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


    /**
     * Decodes the header into a username and password or session.
     *
     * @throws BadCredentialsException if the  header is not present or is not valid Base64
     */
    private String[] extractAndDecodeHeader(String header, HttpServletRequest request, String authOption) throws IOException {

        byte[] base64Token = header.substring(authOption.length()).getBytes("UTF-8");
        byte[] decoded;

        try {
            decoded = Base64.decode(base64Token);
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }

        String token = new String(decoded, getCredentialsCharset(request));

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
                                              Authentication authResult) throws IOException {
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

    public void setCredentialsCharset(String credentialsCharset) {
        Assert.hasText(credentialsCharset, "credentialsCharset cannot be null or empty");
        this.credentialsCharset = credentialsCharset;
    }

    protected String getCredentialsCharset(HttpServletRequest httpRequest) {
        return credentialsCharset;
    }
}
