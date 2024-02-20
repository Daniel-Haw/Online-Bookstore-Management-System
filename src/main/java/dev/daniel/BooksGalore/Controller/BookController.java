package dev.daniel.BooksGalore.Controller;


import dev.daniel.BooksGalore.Model.Book;
import dev.daniel.BooksGalore.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {
    @Autowired
    private BookService serv;

    @GetMapping("/api/v1/books")
    public ResponseEntity<List<Book>> getAllBooks(){
        try{
            List<Book> books = serv.listAll();
            return new ResponseEntity<>(books,HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/api/v1/books/{id}")
    public ResponseEntity<Book> getSingleBook(@PathVariable String id){
        try{
            Book book = serv.getOne(id);
            return new ResponseEntity<>(book,HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/api/v1/books/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable String id){
        try{
            serv.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/api/v1/books/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        try{
            Book addedBook = serv.addBook(book);
            return new ResponseEntity<>(addedBook,HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/api/v1/books/edit")
    public ResponseEntity<Book> editBook(@RequestBody Book book){
        try{
            Book updatedBook = serv.editBook(book);
            return new ResponseEntity<>(updatedBook,HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }
}
