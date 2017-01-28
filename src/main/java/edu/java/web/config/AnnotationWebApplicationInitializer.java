package edu.java.web.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by edwin on 19/01/17.
 */
public class AnnotationWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { RootAppConfig.class, WebMvcConfigurationAdapter.class };
        }

    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebMvcConfigurationAdapter.class };
    }

    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
