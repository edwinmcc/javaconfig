package edu.java.web.beans;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by edwin on 18/01/17.
 */
public class AppContextRef implements ApplicationContextAware {

    private static WebApplicationContext context;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AppContextRef.context=(WebApplicationContext)applicationContext;
    }

    public WebApplicationContext getContext() {
        return context;
    }

}
