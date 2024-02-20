package dev.daniel.BooksGalore.Controller;

import dev.daniel.BooksGalore.Model.Review;
import dev.daniel.BooksGalore.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ReviewController {
    @Autowired
    private ReviewService serv;

    @GetMapping("/api/v1/books/{id}/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable String id){
        try{
            List<Review> reviews = serv.listAll(id);
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    @PostMapping("api")
}
