package com.sorhive.comprojectserver.config;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sorhive.comprojectserver"})
public class ComprojectServerApplication {


    public static final String APPLICATION_LOCATIONS = "spring.config.location="
            + "classpath:application.yml, "
            + "classpath:application-s3.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(ComprojectServerApplication.class)
                .properties(APPLICATION_LOCATIONS)
                .run(args);
    }

}
