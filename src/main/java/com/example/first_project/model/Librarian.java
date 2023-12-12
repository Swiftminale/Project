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
public class Librarian {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Lid;
    @NotNull(message = "First name can not be Empty!")
    private String firstname
            ;
    @NotNull(message = "Last name can not be empty!")
    private String lastname;
    @Enumerated
    private Gender gender;
}
