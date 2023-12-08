package com.example.first_project.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Students{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sid;

    private String firstname;
    private String lastname;
    @Enumerated
    private Gender gender;
    private Long age;
}


