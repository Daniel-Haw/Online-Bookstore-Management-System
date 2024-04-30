package dev.daniel.BooksGalore.Registration;

import dev.daniel.BooksGalore.Model.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/registration")
public class RegistrationController {

    @Autowired
    private RegistrationService serv;

    @PostMapping
    public ResponseEntity<String> register(@RequestBody RegistrationRequest request){
        try{
            serv.register(request);
            return ResponseEntity.ok().body("{\"message\": \"User registered successfully\"}");
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("{\"error\": \"Email already exists\"}");
        }
    }
}
