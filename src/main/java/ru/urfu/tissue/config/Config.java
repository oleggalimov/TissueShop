package ru.urfu.tissue.config;


import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.urfu.tissue.aop.Checkorder;
import ru.urfu.tissue.utils.ConnectionCreator;

import java.sql.Connection;
import java.sql.SQLException;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan (basePackages = "ru.urfu.tissue")
public class Config implements WebMvcConfigurer  {

    @Bean

    public Connection ConnectionCreator () throws SQLException {
        return new ConnectionCreator().createConnection();
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/webjars/**",
                "/img/**",
                "/css/**",
                "/js/**")
                .addResourceLocations(
                        "classpath:/META-INF/resources/webjars/",
                        "classpath:/static/img/",
                        "classpath:/static/css/",
                        "classpath:/static/js/");
    }
    @Bean
    public Checkorder createCheckOrder () {
        return new Checkorder();
    }


}
