package com.example.first_project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Books {

    @Id
    @GeneratedValue
    private Long bookId;

    private String BookName;
    private String Author;
    private String genre;
    private boolean available;


    @ManyToOne
    private Students borrower;

}
