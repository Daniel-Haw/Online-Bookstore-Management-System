package dev.daniel.BooksGalore;

import dev.daniel.BooksGalore.Model.Role;
import dev.daniel.BooksGalore.Model.User;
import dev.daniel.BooksGalore.Registration.RegistrationService;
import dev.daniel.BooksGalore.Service.UserService;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Comment;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class BooksGaloreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksGaloreApplication.class, args);
	}

}
