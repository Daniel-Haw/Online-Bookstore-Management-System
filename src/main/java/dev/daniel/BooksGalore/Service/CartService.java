package dev.daniel.BooksGalore.Service;

import dev.daniel.BooksGalore.Model.Book;
import dev.daniel.BooksGalore.Model.Cart;
import dev.daniel.BooksGalore.Repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;

    public Cart addBook(Book book, Cart userCart) {
        List<Book> userBooks = userCart.getBooks();
        userBooks.add(book);
        return cartRepository.save(userCart);
    }

    public Cart removeBook(Book book, Cart userCart) {
        List<Book> userBooks = userCart.getBooks();
        userBooks.remove(book);
        return cartRepository.save(userCart);
    }
}
