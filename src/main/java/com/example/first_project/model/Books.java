package com.example.first_project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    @NotNull
    private String bookName;
    @NotNull
    private String Author;
    @NotNull
    private String genre;
    private boolean borrowed;



    @ManyToOne
    private Students borrowedBy;




}
