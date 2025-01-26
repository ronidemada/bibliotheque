package com.springboot.bibliotheque.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Name for User is required")
    private String name;

    @Email(message = "Please enter a valid email")
    @Column(unique=true)
    private String email;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Borrowing> borrowings = new ArrayList<>();
}
