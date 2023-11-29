package com.example.first_project.service;


import com.example.first_project.model.Books;
import com.example.first_project.model.Students;
import com.example.first_project.repository.BooksRepository;
import com.example.first_project.repository.StudentsRepository;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import java.util.stream.Collectors;

@Service
public class LibraryService {

    private final BooksRepository booksRepository;
    private final StudentsRepository studentsRepository;

    public LibraryService(BooksRepository booksRepository, StudentsRepository studentsRepository) {
        this.booksRepository = booksRepository;
        this.studentsRepository = studentsRepository;
    }

    public List<Books> getAllBooks() {
        List<Books> books = booksRepository.findAll();
        for (Books books1 : books){
            if (books1.getBorrower() != null){
                books1.getBorrower().getId();
            }
        }

        return books;
    }

    public List<Books> getAvailableBooks() {
        return booksRepository.findByAvailableTrue();
    }

    public Optional<Students> getStudentsById(Long id) {
        return studentsRepository.findById(id);
    }

    public List<Books> getBorrowedBooksByStudent(Long id) {
        return getStudentsById(id)
                .map(Students::getBorrowedBooks)
                .orElse(Collections.emptyList());
    }

//    public List<Books> getAllBorrowedBooks() {
//        return studentsRepository.findAll().stream()
//                .flatMap(student -> student.getBorrowedBooks().stream())
//                .collect(Collectors.toList());
//    }

    public boolean borrowBook(Long id, Long bookId) {
        Optional<Students> studentsOptional = studentsRepository.findById(id);
        Optional<Books> booksOptional = booksRepository.findById(bookId);

        if (studentsOptional.isPresent() && booksOptional.isPresent() && booksOptional.get().isAvailable()) {
            Books books = booksOptional.get();
            books.setAvailable(false);
            booksRepository.save(books);
            return true;
        }
        return false;
    }
}
