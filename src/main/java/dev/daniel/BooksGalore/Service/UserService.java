package dev.daniel.BooksGalore.Service;

import dev.daniel.BooksGalore.Model.User;
import dev.daniel.BooksGalore.Repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository repo;

    public User findUserByEmail(String name) {
        return repo.findByEmail(name).orElseThrow(() -> new UsernameNotFoundException("User with" + name + "not found"));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repo.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with email" + email + " not found"));
    }

    public User signUp(User user){
        boolean userExists = repo.findByEmail(user.getEmail()).isPresent();
        if(userExists){
           throw new IllegalArgumentException("email already taken");
        }

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        return repo.save(user);
    }
}
