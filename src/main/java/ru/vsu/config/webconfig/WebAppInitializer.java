package ru.vsu.config.webconfig;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import ru.vsu.config.ServiceConfig;
import ru.vsu.config.securityconfig.SecurityConfig;
import ru.vsu.config.securityconfig.SessionFilter;

import javax.servlet.Filter;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{ServiceConfig.class,SecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class};
    }

    /*@Override
    protected Filter[] getServletFilters() {
        return new Filter[] { new SessionFilter() };
    }*/
}
