package dev.daniel.BooksGalore.Controller;

import dev.daniel.BooksGalore.Model.Book;
import dev.daniel.BooksGalore.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/")
public class AdminController {

    @Autowired
    private BookService bookService;
    @PostMapping("add")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        try{
            Book addedBook = bookService.addBook(book);
            return new ResponseEntity<>(addedBook,HttpStatus.CREATED);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable String id){
        try{
            bookService.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
