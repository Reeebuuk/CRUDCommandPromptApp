package com.uzelac;

import com.uzelac.model.exception.QuitException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.io.IOException;

@Configuration
@ComponentScan
@EnableJpaRepositories
@EnableAutoConfiguration
public class Main
{
    public static void main(String[] args) throws IOException
    {
        ConfigurableApplicationContext run = SpringApplication.run(Main.class, args);

        try
        {
            run.getBean(CLI.class).readInput();
        }
        catch (QuitException e)
        {
            System.exit(1);
        }
    }
}