package com.github.psycomentis06.isatexbackendservice;

import com.github.psycomentis06.isatexbackendservice.service.BaseDBData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class IsatexBackendServiceApplication {

    private final BaseDBData baseDBData;

    public IsatexBackendServiceApplication(BaseDBData baseDBData) {
        this.baseDBData = baseDBData;
    }

    public static void main(String[] args) {
        SpringApplication.run(IsatexBackendServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner defaultData() {
        return args -> {
            baseDBData.saveRequiredRoles();
            baseDBData.saveRequiredUser();
        };
    }
}
