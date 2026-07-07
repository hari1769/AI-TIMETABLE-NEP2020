package com.nep.timetable;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableJpaAuditing
@EnableCaching
@EnableAsync
@EnableScheduling
@OpenAPIDefinition(
    info = @Info(
        title = "NEP Timetable System API",
        version = "1.0.0",
        description = "AI-Based Timetable Generation System aligned with NEP 2020 for Multidisciplinary Education Structures",
        contact = @Contact(
            name = "NEP Timetable Team",
            email = "admin@nep-timetable.edu.in"
        ),
        license = @License(
            name = "MIT License",
            url = "https://opensource.org/licenses/MIT"
        )
    )
)
public class TimetableSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimetableSystemApplication.class, args);
    }
}
