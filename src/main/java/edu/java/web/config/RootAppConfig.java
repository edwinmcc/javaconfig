package edu.java.web.config;

//import edu.java.web.beans.Batch;
import edu.java.web.config.security.CustomAccessDeniedHandler;
import edu.java.web.config.security.CustomSuccessHandler;
import edu.java.web.config.security.UserServiceDetailsServiceImpl;
import edu.java.web.config.security.UsernamePasswordAuthenticationProvider;
import edu.java.web.dao.BatchDAO;
import edu.java.web.dao.BatchDAOImpl;
import edu.java.web.dao.UserDAO;
import edu.java.web.dao.UserDAOImpl;
import edu.java.web.service.BatchService;
import edu.java.web.service.BatchServiceImpl;
import edu.java.web.service.UserService;
import edu.java.web.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultRedirectStrategy;

import java.util.Properties;

/**
 * Created by edwin on 17/01/17.
 */
@Configuration
@ComponentScan(
        basePackages = {
                "edu.java.web",
                "edu.java.web.beans",
                "edu.java.web.config",
                "edu.java.web.controller",
                "edu.java.web.config.security"
})
@PropertySource(value="classpath:hibernate.properties",ignoreResourceNotFound = true)
public class RootAppConfig {

    @Autowired
    Environment env;

    @Bean(name="sessionFactory")
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean=new LocalSessionFactoryBean();
        sessionFactoryBean.setHibernateProperties(getHibernateProperties());
        sessionFactoryBean.setAnnotatedClasses(
                edu.java.web.beans.User.class,
                edu.java.web.beans.Role.class,
                edu.java.web.beans.Batch.class,
                edu.java.web.beans.BatchEntry.class
        );
        return sessionFactoryBean;
    }

    public Properties getHibernateProperties() {
        Properties p=new Properties();
        try {
            p.setProperty("hibernate.dialect",env.getProperty("hibernate.dialect"));
            p.setProperty("hibernate.connection.driver_class",env.getProperty("hibernate.connection.driver_class"));
            p.setProperty("hibernate.connection.url",env.getProperty("hibernate.connection.url"));
            p.setProperty("hibernate.connection.username",env.getProperty("hibernate.connection.username"));
            p.setProperty("hibernate.connection.password",env.getProperty("hibernate.connection.password"));
            p.setProperty("hibernate.connection.pool_size",env.getProperty("hibernate.connection.pool_size"));
            p.setProperty("hibernate.current_session_context_class",env.getProperty("hibernate.current_session_context_class"));
            p.setProperty("hibernate.cache.provider_class",env.getProperty("hibernate.cache.provider_class"));
            p.setProperty("hibernate.cache.provider_class",env.getProperty("hibernate.cache.provider_class"));
            p.setProperty("hibernate.show_sql",env.getProperty("hibernate.show_sql"));
            p.setProperty("hibernate.format_sql",env.getProperty("hibernate.format_sql"));
            p.setProperty("hibernate.hbm2ddl.auto",env.getProperty("hibernate.hbm2ddl.auto"));
            p.setProperty("hibernate.cp30.min_size",env.getProperty("hibernate.cp30.min_size"));
            p.setProperty("hibernate.cp30.max_size",env.getProperty("hibernate.cp30.max_size"));
            p.setProperty("hibernate.cp30.timeout",env.getProperty("hibernate.cp30.timeout"));
            p.setProperty("hibernate.cp30.max_statements",env.getProperty("hibernate.cp30.max_statements"));
            p.setProperty("hibernate.cp30.idle_test_period",env.getProperty("hibernate.cp30.idle_test_period"));
        }
        catch (Exception ioe) {
            //TODO: take appropriate action.
        }
        return p;
    }

    @Bean(name="authenticationProvider")
    public UsernamePasswordAuthenticationProvider authenticationProvider() {
        return new UsernamePasswordAuthenticationProvider();
    }

    @Bean(name="customSuccessHandler")
    public CustomSuccessHandler customSuccessHandler() {
        CustomSuccessHandler customSuccessHandler=new CustomSuccessHandler();
        return customSuccessHandler;
    }

    @Bean(name="accessDeniedHandler")
    public CustomAccessDeniedHandler accessDeniedHandler() {
        CustomAccessDeniedHandler customAccessDeniedHandler=new CustomAccessDeniedHandler();
        return customAccessDeniedHandler;
    }

    @Bean(name="redirectStrategyRef")
    public DefaultRedirectStrategy redirectStrategyRef() {
        DefaultRedirectStrategy redirectStrategy=new DefaultRedirectStrategy();
        return redirectStrategy;
    }

    @Bean(name="userDetailsService")
    public UserDetailsService userDetailsService() {
        return new UserServiceDetailsServiceImpl();
    }

    @Bean(name="userDAO")
    public UserDAO  userDAO() {
        UserDAO userDAO=new UserDAOImpl();
        return userDAO;
    }

    @Bean(name="userService")
    public UserService userService() {
        UserService userService=new UserServiceImpl();
        return userService;
    }


    @Bean(name="batchDAO")
    public BatchDAO batchDAO() {
        BatchDAO batchDAO=new BatchDAOImpl();
        return batchDAO;
    }

    @Bean(name="batchService")
    public BatchService batchService() {
        BatchService batchService=new BatchServiceImpl();
        return batchService;
    }

}
