package dev.daniel.BooksGalore.Controller;

import dev.daniel.BooksGalore.Model.Book;
import dev.daniel.BooksGalore.Model.Review;
import dev.daniel.BooksGalore.Model.User;
import dev.daniel.BooksGalore.Service.ReviewService;
import dev.daniel.BooksGalore.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    @Autowired
    private ReviewService serv;
    @Autowired
    private UserService userService;

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable int id, Principal principal){
        try{
            User authenticatedUser = userService.findUserByEmail(principal.getName());
            serv.deleteReview(id,authenticatedUser);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("add")
    public ResponseEntity<Review> addReview(@RequestBody Review review, Principal principal){
        try{
            User authenticatedUser = userService.findUserByEmail(principal.getName());
            Review newReview = serv.addReview(review,authenticatedUser);
            return new ResponseEntity<>(newReview,HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    @PutMapping("edit/{id}")
    public ResponseEntity<Review> editReview(@PathVariable int id, @RequestBody Review review, Principal principal){
        try{
            User authenticatedUser = userService.findUserByEmail(principal.getName());
            Review updatedReview = serv.updateReview(id, review, authenticatedUser);
            return new ResponseEntity<>(updatedReview,HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

}
