package dev.daniel.BooksGalore.Controller;


import dev.daniel.BooksGalore.Model.Book;
import dev.daniel.BooksGalore.Model.Review;
import dev.daniel.BooksGalore.Service.BookService;
import dev.daniel.BooksGalore.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/books/")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("all")
    public ResponseEntity<List<Book>> getAllBooks(){
        try{
            List<Book> books = bookService.listAll();
            return new ResponseEntity<>(books,HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

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

}
