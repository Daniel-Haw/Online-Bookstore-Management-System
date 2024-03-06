package dev.daniel.BooksGalore.Service;

import dev.daniel.BooksGalore.Exception.BookNotFoundException;
import dev.daniel.BooksGalore.Exception.ReviewNotFoundException;
import dev.daniel.BooksGalore.Exception.UnauthorizedException;
import dev.daniel.BooksGalore.Model.Review;
import dev.daniel.BooksGalore.Model.User;
import dev.daniel.BooksGalore.Repository.BookRepository;
import dev.daniel.BooksGalore.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository repo;
    @Autowired
    private BookRepository bookRepo;

    public List<Review> listAll(String ISBNid){
        return repo.findAllByBook_ISBNid(ISBNid);
    }

    public Review addReview(String ISBNId, Review review, User authenticatedUser){
        LocalDate currentDate = LocalDate.now();
        review.setBook(bookRepo.findById(ISBNId).orElseThrow(()->new BookNotFoundException("Book with id " + ISBNId + " does not exist")));
        review.setUser(authenticatedUser);
        review.setDate(currentDate);
        return repo.save(review);
    }

    public Review updateReview(String ISBNId, int id, Review review ,User authenticatedUser){
        Review existingReview = repo.findById(id).orElseThrow(() -> new ReviewNotFoundException("Review not found"));
        if(!existingReview.getUser().equals(authenticatedUser)){
            throw new UnauthorizedException("You are not authorized to edit this review");
        }
        review.setBook(bookRepo.findById(ISBNId).orElseThrow(()->new BookNotFoundException("Book with id " + ISBNId + " does not exist")));
        review.setId(id);
        review.setUser(authenticatedUser);
        LocalDate currentDate = LocalDate.now();
        review.setDate(currentDate);
        return repo.save(review);
    }
    public void deleteReview(int id, User authenticatedUser){
        Review review = repo.findById(id).orElseThrow(() -> new ReviewNotFoundException("Review not found"));
        if(!review.getUser().equals(authenticatedUser)){
            throw new UnauthorizedException("You are not authorized to delete this review");
        }
        repo.deleteById(id);
    }

}
