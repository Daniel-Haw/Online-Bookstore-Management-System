package dev.daniel.BooksGalore.Registration;

import dev.daniel.BooksGalore.Model.Role;
import dev.daniel.BooksGalore.Model.User;
import dev.daniel.BooksGalore.Service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService serv;
    public User register(RegistrationRequest request){
        return serv.signUp(
            User.builder()
                .firstname(request.getFirstname())
                    .lastname(request.getLastname())
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .role(Role.USER)
                    .build()
        );
    }
}
