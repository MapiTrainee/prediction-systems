package xyz.pietryga.predsys.config;

import org.h2.server.web.WebServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
public class WebConfiguration {

    private final static Logger LOGGER = LoggerFactory.getLogger(WebConfiguration.class);

    @Configuration
    @Profile(Profiles.DEVELOPMENT)
    public static class EmbeddedH2Config {

        @Bean
        ServletRegistrationBean h2servletRegistrationBean() {
            ServletRegistrationBean registrationBean = new ServletRegistrationBean(new WebServlet());
            registrationBean.addUrlMappings("/console/*");
            return registrationBean;
        }

    }

    @Configuration
    @EnableSwagger2
    public class SwaggerConfig {

        @Bean
        public Docket api() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("xyz.pietryga.predsys.web"))
                    .paths(PathSelectors.any())
                    .build();
        }
    }

}
