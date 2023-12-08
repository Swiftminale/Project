package com.example.first_project.utils;

import com.example.first_project.service.BooksServices;
import com.example.first_project.service.StudentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
@RequiredArgsConstructor
public class InitServices {
    private final StudentsService studentsService;
    private final BooksServices booksServices;
//    @PostConstruct
//    public void onInit() {
//        studentsService.initStudent();
//    }
}
