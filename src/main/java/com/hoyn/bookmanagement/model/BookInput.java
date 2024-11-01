package com.hoyn.bookmanagement.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookInput {
    private String name;
    private Integer pageCount;
    private Long authorId;
}
