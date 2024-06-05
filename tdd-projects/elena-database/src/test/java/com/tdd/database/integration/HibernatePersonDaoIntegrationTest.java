package com.tdd.database.integration;

import com.tdd.database.entity.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import static  org.junit.Assert.*;

/**
 * 1, Integration tests with an in-memory database.
 * Caution: all the objects of Hibernate are real.
 * */
public class HibernatePersonDaoIntegrationTest {

    /**
     * This test is failed to pass because there is something wrong with the configuration of Hibernate.
     * I
     * */
    @Test
    public void persistedObjectExistsInDatabase() throws Exception {

        SessionFactory factory = getSessionFactory();
        HibernateIntegrationPersonDao dao = new HibernateIntegrationPersonDao();
        dao.setSessionFactory(factory);

        Person person = new Person("Tom", "Hanks");
        dao.save(person);

        assertNotNull(person.getId());

        Session s = factory.openSession();


    }

    // To get a real SessionFactory from configuration files.
    private SessionFactory getSessionFactory() throws Exception {
        Configuration config = createConfiguration();
        return config.buildSessionFactory();
    }

    private Configuration createConfiguration() throws  Exception {
        Configuration cfg = loadProductionConfiguration();
        String path = "/hibernate.test.properties";
        loadTestConfigurationInfo(cfg, path);
        return cfg;
    }

    public Configuration loadProductionConfiguration() {
        return new Configuration().configure();
    }

    private void loadTestConfigurationInfo(Configuration cfg, String path) throws Exception {
        Properties properties = loadPropertiesFrom(path);
        Enumeration<Object> keys = properties.keys();
        while (keys.hasMoreElements()) {
            String key = (String)keys.nextElement();
            String value = properties.getProperty(key);
            // override individual properties
            cfg.setProperty(key,value);
        }

    }


    private Properties loadPropertiesFrom(String path) throws Exception {
        InputStream stream = getClass().getResourceAsStream(path);
        assertNotNull("Resource is not found" + path, stream);
        Properties properties = new Properties();
        properties.load(stream);
        stream.close();
        return properties;
    }

}
