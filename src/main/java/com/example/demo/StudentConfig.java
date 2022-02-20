package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository repository) {
        return args -> {
            Student ahmad = new Student(
                    "ahmad",
                    "foroughi",
                    "ahmadf0311@gmail.com",
                    LocalDate.of(1988, Month.APRIL, 28)
            );

            Student fariba = new Student(
                    "fariba",
                    "pourayoubi",
                    "faribap0311@gmail.com",
                    LocalDate.of(1987, Month.NOVEMBER, 20)
            );
            repository.saveAll(List.of(ahmad, fariba));

        };
    }
}

