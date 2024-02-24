package dev.daniel.BooksGalore.Registration;

import dev.daniel.BooksGalore.Model.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public User register(@RequestBody RegistrationRequest request){
        return serv.register(request);
    }
}
