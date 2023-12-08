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
    private String bookName;
    private String Author;
    private String genre;
    private boolean borrowed;



    @ManyToOne
    private Students borrowedBy;




}
