package dev.daniel.BooksGalore.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "book")
public class Book {
    @Id
    @JsonProperty("ISBNid")
    private String ISBNid;
    private String title;
    private String genre;
    private String description;
    private double price;
    @JsonBackReference
    @OneToMany(mappedBy = "book", cascade = CascadeType.MERGE)
    private List<Review> review;
    @ManyToMany
    private List<User> user;
}
