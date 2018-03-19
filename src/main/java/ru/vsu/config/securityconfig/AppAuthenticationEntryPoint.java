package ru.vsu.config.securityconfig;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class AppAuthenticationEntryPoint extends BasicAuthenticationEntryPoint {

    /* Поскольку приложение использует базовую аутентификацию и не имеет страницу залогинивания
    * то в случае, когда аутентификация  прошла  а авторизация нет, то
     * должно быть сообщение об ощибке с кодом 401
    * */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authEx)
            throws IOException, ServletException {
        response.addHeader("WWW-Authenticate", "Basic realm=" + getRealmName());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
       //// PrintWriter writer = response.getWriter();
       // writer.println("HTTP Status 401 - " + authEx.getMessage());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        setRealmName("RealName");
        super.afterPropertiesSet();
    }
}
