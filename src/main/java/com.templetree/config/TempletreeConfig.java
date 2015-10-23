package com.templetree.config;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.slf4j.Logger;

import java.io.FileInputStream;
import java.util.Properties;

import static org.slf4j.LoggerFactory.getLogger;


/**
 * Created by Lalith on 10/23/15.
 */
public class TempletreeConfig implements ServletContextListener {

    private static final Logger LOGGER = getLogger(TempletreeConfig.class);

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

        Properties properties = new Properties();
        String externalFile = "";
        try {
            properties.load(servletContextEvent.getServletContext().getResourceAsStream("/WEB-INF/spring/application.properties"));
            externalFile = System.getProperty("templetreeAppProperty") != null ? System.getProperty("templetreeAppProperty") : System.getenv("templetreeAppProperty");
            properties.load(new FileInputStream(externalFile));
        } catch (Exception e) {
            LOGGER.error("Property reading error from location [ " + externalFile + " ]", e);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
