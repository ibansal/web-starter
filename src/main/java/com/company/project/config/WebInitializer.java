package com.company.project.config;


import com.company.project.utils.PropertiesLoader;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Properties;

public class WebInitializer extends
        AbstractAnnotationConfigDispatcherServletInitializer {

    private static final PropertiesLoader propertiesLoader = new PropertiesLoader();

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringWebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null;
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        super.onStartup(servletContext);
        Properties prop = propertiesLoader.load("system.properties");
        String spring_profiles_active = prop.getProperty("active.profile", "dev");
        System.out.println("Env property : " + spring_profiles_active);
        if (StringUtils.isBlank(spring_profiles_active)) {
            spring_profiles_active = "dev";
        }
        servletContext.setInitParameter("spring.profiles.active", spring_profiles_active);
    }

}