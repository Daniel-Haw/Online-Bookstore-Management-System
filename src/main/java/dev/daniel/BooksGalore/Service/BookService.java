package dev.daniel.BooksGalore.Service;

import dev.daniel.BooksGalore.Exception.BookNotFoundException;
import dev.daniel.BooksGalore.Model.Book;
import dev.daniel.BooksGalore.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository repo;

    public List<Book> listAll(){
        return repo.findAll();
    }

    public Book getOne(String id){
        return repo.findById(id).orElseThrow(() -> new BookNotFoundException("Book by ISBNid " + id + " not found"));
    }

    public Book addBook(Book book){
        return repo.save(book);
    }

    public Book editBook(String id,Book updatedBook){
        updatedBook.setISBNid(id);
        return repo.save(updatedBook);
    }

    public void deleteBook(String id){
        repo.deleteById(id);
    }


}
