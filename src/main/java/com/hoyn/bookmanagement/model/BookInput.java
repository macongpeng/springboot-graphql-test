package com.hoyn.bookmanagement.model;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
public class BookInput {
    private String name;
    private Integer pageCount;
    private Long authorId;
}
