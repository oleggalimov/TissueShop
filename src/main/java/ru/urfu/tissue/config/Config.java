package ru.urfu.tissue.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.urfu.tissue.aop.Checkorder;
import ru.urfu.tissue.utils.ConnectionCreator;

@Configuration
@EnableWebMvc
@EnableAspectJAutoProxy
@ComponentScan (basePackages = "ru.urfu.tissue")
public class Config implements WebMvcConfigurer {

    @Bean
    public ConnectionCreator ConnectionCreator () {
        return new ConnectionCreator();
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
