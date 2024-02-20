package dev.daniel.BooksGalore.Service;

import dev.daniel.BooksGalore.Model.Review;
import dev.daniel.BooksGalore.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository repo;

    public List<Review> listAll(String ISBNid){
        return repo.findAllByISBNid(ISBNid);
    }

    public Review addReview(Review review){
        return repo.save(review);
    }

    public Review updateReview(Review review){
        return repo.save(review);
    }
    public void deleteReview(int id){
        repo.deleteById(id);
    }

}
