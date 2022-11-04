package com.sorhive.comprojectserver;

import com.sorhive.comprojectserver.common.jpa.RangeableRepositoryImpl;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableJpaRepositories(repositoryBaseClass = RangeableRepositoryImpl.class)
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
