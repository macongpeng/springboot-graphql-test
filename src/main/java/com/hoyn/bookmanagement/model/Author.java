package com.hoyn.bookmanagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    Long id;

    @Column
    String name;

    /*public Author(Long id, String name) {
        this.id = id;
        this.name = name;
    }*/
}
