package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository) {
		return args -> {
			Student john = new Student(
						1L,
						"John",
						"johnsmith@gmail.com",
						LocalDate.of(1998, Month.AUGUST, 4)
					);

			Student joe = new Student(
						"Joe",
						"joeblake@gmail.com",
						LocalDate.of(1999, Month.JULY, 9)
					);

			Student mary = new Student(
					"Mary",
					"marysmith@gmail.com",
					LocalDate.of(2005, Month.JUNE, 29)
				);

			repository.saveAll(List.of(john, joe, mary));
		};
	}
}
