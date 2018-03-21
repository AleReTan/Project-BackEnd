package ru.vsu.config.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.vsu.services.security.MyUserDetailsService;
import ru.vsu.services.serviceImpl.SessionService;
import ru.vsu.services.serviceImpl.UserService;


@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"ru.vsu.services.security"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private MyUserDetailsService myUserDetailsService;
    private AppAuthenticationEntryPoint authenticationEntryPoint;
    private SessionService sessionService;
    private UserService userService;

    @Autowired
    public SecurityConfig(MyUserDetailsService myUserDetailsService, AppAuthenticationEntryPoint appAuthenticationEntryPoint, SessionService sessionService,UserService userService) {
        this.myUserDetailsService = myUserDetailsService;
        this.authenticationEntryPoint = appAuthenticationEntryPoint;
        this.sessionService = sessionService;
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/admin/**").hasAuthority("ADMIN")
                .antMatchers("/login/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/drivers/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/cars/**").hasAnyAuthority("ADMIN", "USER")
                .antMatchers("/json/**").hasAnyAuthority("ADMIN", "USER")
                //.antMatchers("/json/**").hasAnyAuthority("ADMIN", "USER")
                .and().addFilterAfter(new SessionAuthentificationFilter( authenticationManager(),
                authenticationEntryPoint, sessionService, userService),BasicAuthenticationFilter.class)
                .logout().permitAll().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .addLogoutHandler(new SessionClearingLogoutHandler(sessionService)).logoutSuccessUrl("/successLogout");
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
        //.passwordEncoder(encoder()); Включение кодирования пароля.
    }

    //  метод, который указывает на то, что секьюрити игнорирует файлы ресурсов
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    /**
     * бин инициализирующий BCryptPasswordEncoder(11), который отвечает за кодирование и раскодирование пароля
     *
     * @return объект типа {@code BCryptPasswordEncoder}
     */
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(11);
    }
}
