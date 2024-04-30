package dev.daniel.BooksGalore.Controller;

import dev.daniel.BooksGalore.Model.Book;
import dev.daniel.BooksGalore.Model.Cart;
import dev.daniel.BooksGalore.Model.User;
import dev.daniel.BooksGalore.Service.CartService;
import dev.daniel.BooksGalore.Service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cart/")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    @GetMapping("getBooks")
    public ResponseEntity<List<Book>> getBooks(Principal principal){
        try {
            User user = userService.findUserByEmail(principal.getName());
            List<Book> books = user.getCart().getBooks();
            return new ResponseEntity<>(books, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    @PostMapping("addBook")
    public ResponseEntity<Cart> addItemToCart(@RequestBody Book book, Principal principal){
        try {
            User user = userService.findUserByEmail(principal.getName());
            Cart userCart = user.getCart();
            Cart updatedCart = cartService.addBook(book,userCart);
            return new ResponseEntity<>(updatedCart, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    @PostMapping("removeBook/{bookId}")
    public ResponseEntity<Cart> removeItemFromCart(@PathVariable String bookId, Principal principal){
        try {
            User user = userService.findUserByEmail(principal.getName());
            Cart userCart = user.getCart();
            Cart updatedCart = cartService.removeBook(bookId,userCart);
            return new ResponseEntity<>(updatedCart, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }

    @GetMapping("getTotalPrice")
    public ResponseEntity<Double> getTotalPrice(Principal principal){
        try{
            User user = userService.findUserByEmail(principal.getName());
            Cart userCart = user.getCart();
            double totalPrice = cartService.calculateTotalPrice(userCart);
            return new ResponseEntity<>(totalPrice, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
    }
}

//CartController