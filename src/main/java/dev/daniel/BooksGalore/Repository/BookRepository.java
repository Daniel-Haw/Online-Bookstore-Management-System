package dev.daniel.BooksGalore.Repository;

import dev.daniel.BooksGalore.Model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,String> {
    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    Page<Book> findByGenreContainingIgnoreCase(String genre, Pageable pageable);

    Page<Book> findByTitleContainingIgnoreCaseAndGenreContainingIgnoreCase(String title, String genre, Pageable pageable);

    @Query("SELECT DISTINCT b.genre FROM Book b")
    List<String> findDistinctGenres();
}
