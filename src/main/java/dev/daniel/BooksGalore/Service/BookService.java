package dev.daniel.BooksGalore.Service;

import dev.daniel.BooksGalore.Exception.BookNotFoundException;
import dev.daniel.BooksGalore.Model.Book;
import dev.daniel.BooksGalore.Model.Review;
import dev.daniel.BooksGalore.Repository.BookRepository;
import dev.daniel.BooksGalore.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private BookRepository repo;

    public Book getOne(String id) {
        return repo.findById(id).orElseThrow(() -> new BookNotFoundException("Book by ISBNid " + id + " not found"));
    }

    public Book addBook(Book book) {
        return repo.save(book);
    }

    public Book editBook(String id, Book updatedBook) {
        updatedBook.setISBNid(id);
        return repo.save(updatedBook);
    }

    public void deleteBook(String id) {
        repo.deleteById(id);
    }


    public double calculateAverageRating(String ISBNid) {
        List<Review> reviews = reviewRepository.findAllByBook_ISBNid(ISBNid);
        double averageRating = reviews.stream()
                .mapToDouble(Review::getStars)
                .average()
                .orElse(0.0);
        return averageRating;
    }

    public Map<Integer, Long> getIndividualRatings(String ISBNid) {
        List<Review> reviews = reviewRepository.findAllByBook_ISBNid(ISBNid);
        Map<Integer,Long> ratings = reviews.stream().collect(Collectors.groupingBy(Review::getStars, Collectors.counting()));
        return ratings;
    }

    public Page<Book> searchByTitleAndSortByGenre(String title, Pageable pageable,String genre) {
        if (title != null && genre != null) {
            return repo.findByTitleContainingIgnoreCaseAndGenreContainingIgnoreCase(title, genre, pageable);
        } else if (title != null) {
            return repo.findByTitleContainingIgnoreCase(title, pageable);
        } else if (genre != null) {
            return repo.findByGenreContainingIgnoreCase(genre, pageable);
        } else{
            return repo.findAll(pageable);
        }
    }

    public List<String> getListOfGenres() {
        return repo.findDistinctGenres();
    }
}
