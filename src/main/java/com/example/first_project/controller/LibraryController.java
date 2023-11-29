package com.example.first_project.controller;

import com.example.first_project.model.Books;
import com.example.first_project.model.Students;
import com.example.first_project.service.LibraryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Library")
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/allBooks")
    public List<Books> getAllBooks() {
        libraryService.getAllBooks();
        return libraryService.getAllBooks();
    }

    @GetMapping("/available-books")
    public List<Books> getAvailableBooks() {
        return libraryService.getAvailableBooks();
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Students> getStudents(@PathVariable long id) {
        Optional<Students> studentsOptional = libraryService.getStudentsById(id);
        return studentsOptional.map(ResponseEntity::ok)
                .orElseThrow(() -> new EntityNotFoundException("Student not found"));
    }

    @GetMapping("/borrowed-books/{id}")
    public ResponseEntity<List<Books>> getBorrowedBooksByStudent(@PathVariable Long id) {
        List<Books> borrowedBooks = libraryService.getBorrowedBooksByStudent(id);
        return borrowedBooks.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(borrowedBooks);
    }

    @PostMapping("/borrowBook/{id}/{bookID}")
    public ResponseEntity<String> borrowBook(@PathVariable Long id, @PathVariable Long bookID) {
        boolean success = libraryService.borrowBook(id, bookID);

        if (success) {
            return ResponseEntity.ok("Book borrowed successfully");
        } else {
            return ResponseEntity.badRequest().body("Book not available or student not found.");
        }
    }
}
