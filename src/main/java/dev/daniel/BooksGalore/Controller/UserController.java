package dev.daniel.BooksGalore.Controller;

import dev.daniel.BooksGalore.Model.User;
import dev.daniel.BooksGalore.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/user/")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("status")
    public boolean getUserStatus(Principal principal){
        return principal != null;
    }

    @GetMapping("current")
    public ResponseEntity<User> getUser(Principal principal){
        try {
            if(principal == null) return null;
            User user = userService.findUserByEmail(principal.getName());
            return new ResponseEntity<>(user,HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }
}
