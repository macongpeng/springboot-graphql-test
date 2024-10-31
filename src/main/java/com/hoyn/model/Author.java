package com.hoyn.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor
@Setter
@Getter
public class Author {
        Integer id;
        String name;

        public Author(Integer id, String name) {
            this.id = id;
            this.name = name;
        }
}
