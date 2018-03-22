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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.vsu.services.security.MyUserDetailsService;


@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"ru.vsu.services.security"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private MyUserDetailsService myUserDetailsService;
    private AppAuthenticationEntryPoint appAuthenticationEntryPoint;

    @Autowired
    public SecurityConfig(MyUserDetailsService myUserDetailsService, AppAuthenticationEntryPoint appAuthenticationEntryPoint) {
        this.myUserDetailsService = myUserDetailsService;
        this.appAuthenticationEntryPoint = appAuthenticationEntryPoint;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/rest/user/**").hasAuthority("ADMIN")
                .antMatchers("/**").hasAuthority("USER")
                .and().httpBasic()
                .authenticationEntryPoint(appAuthenticationEntryPoint);
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
