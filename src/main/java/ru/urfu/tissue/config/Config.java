package ru.urfu.tissue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.urfu.tissue.utils.ConnectionCreator;

@Configuration
@EnableWebMvc
@ComponentScan (basePackages = "ru.urfu.tissue")
public class Config {
    @Bean
    public ConnectionCreator ConnectionCreator () {
        return new ConnectionCreator();
    }
}
