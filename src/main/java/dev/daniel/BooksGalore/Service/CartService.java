package dev.daniel.BooksGalore.Service;

import dev.daniel.BooksGalore.Exception.BookNotFoundException;
import dev.daniel.BooksGalore.Model.Book;
import dev.daniel.BooksGalore.Model.Cart;
import dev.daniel.BooksGalore.Repository.BookRepository;
import dev.daniel.BooksGalore.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private BookRepository bookRepository;
    public Cart addBook(Book book, Cart userCart) {
        List<Book> userBooks = userCart.getBooks();
        userBooks.add(book);
        return cartRepository.save(userCart);
    }

    public Cart removeBook(String bookId, Cart userCart) {
        List<Book> userBooks = userCart.getBooks();
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book does not exist"));
        userBooks.remove(book);
        return cartRepository.save(userCart);
    }

    public double calculateTotalPrice(Cart userCart) {
        List<Book> userBooks = userCart.getBooks();
        if(userBooks.isEmpty()) return 0.00;
        return userBooks.stream().mapToDouble(Book::getPrice).sum();
    }
}
