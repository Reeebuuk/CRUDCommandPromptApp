package com.uzelac;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Main.class)
public abstract class AbstractSpringWebContextUtilizing
{
    @Autowired
    protected ApplicationContext appContext;

    protected static String defaultXMLPath;

    public void cleanDatabaseData()
    {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(appContext.getBean(DataSource.class));
        jdbcTemplate.execute("DELETE FROM Server");
    }

    @BeforeClass
    public static void setup()
    {
        defaultXMLPath = "";
    }

    @Before
    public void _setUp() throws Exception
    {
        cleanDatabaseData();
    }
}
