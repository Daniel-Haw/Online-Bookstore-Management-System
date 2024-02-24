package dev.daniel.BooksGalore.Registration;

import dev.daniel.BooksGalore.Model.User;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class RegistrationRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;

}

