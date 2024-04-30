package dev.daniel.BooksGalore.Controller;


import dev.daniel.BooksGalore.Model.Book;
import dev.daniel.BooksGalore.Model.Review;
import dev.daniel.BooksGalore.Service.BookService;
import dev.daniel.BooksGalore.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/public/books/")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("{id}")
    public ResponseEntity<Book> getSingleBook(@PathVariable String id){
        try{
            Book book = bookService.getOne(id);
            return new ResponseEntity<>(book,HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @GetMapping("{ISBNid}/averageRating")
    public ResponseEntity<Double> getAverageRating(@PathVariable String ISBNid) {
        try{
            double avgRating = bookService.calculateAverageRating(ISBNid);
            return new ResponseEntity<>(avgRating,HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @GetMapping("{ISBNid}/individualRatings")
    public ResponseEntity<Map<Integer,Long>> getIndividualRatings(@PathVariable String ISBNid){
        try{
            Map<Integer,Long> ratings = bookService.getIndividualRatings(ISBNid);
            return new ResponseEntity<>(ratings,HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    @GetMapping("{id}/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable String id){
        try{
            List<Review> reviews = reviewService.listAll(id);
            return new ResponseEntity<>(reviews, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    @GetMapping("search")
    public ResponseEntity<Page<Book>> searchByTitle(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String genre,
            @RequestParam String title
    ) {
        try {
            PageRequest pageRequest = PageRequest.of(page, size);
            Page<Book> booksPage = bookService.searchByTitleAndSortByGenre(title, pageRequest, genre);
            return ResponseEntity.ok().body(booksPage);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("allGenres")
    public ResponseEntity<List<String>> listOfGenres(){
        try{
            List<String> genres = bookService.getListOfGenres();
            return new ResponseEntity<>(genres,HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }
}
