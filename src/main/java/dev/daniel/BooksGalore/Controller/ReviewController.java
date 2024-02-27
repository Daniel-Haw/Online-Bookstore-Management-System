package dev.daniel.BooksGalore.Controller;

import dev.daniel.BooksGalore.Model.Book;
import dev.daniel.BooksGalore.Model.Review;
import dev.daniel.BooksGalore.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    @Autowired
    private ReviewService serv;

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable int id){
        try{
            serv.deleteReview(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("add")
    public ResponseEntity<Review> addReview(@RequestBody Review review){
        try{
            Review newReview = serv.addReview(review);
            return new ResponseEntity<>(newReview,HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    @PutMapping("edit")
    public ResponseEntity<Review> editReview(@RequestBody Review review){
        try{
            Review updatedReview = serv.updateReview(review);
            return new ResponseEntity<>(updatedReview,HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

}
