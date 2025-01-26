package com.springboot.bibliotheque.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "BOOK")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Title for Book is required")
    private String title;

    @NotNull(message = "Author for Book is required")
    private String author;

    private boolean isAvailable = true;

    @OneToMany(mappedBy = "book")
    @JsonIgnore
    private List<Borrowing> borrowings = new ArrayList<>();
}
