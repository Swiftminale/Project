package com.example.first_project.controller;

import com.example.first_project.model.Books;
import com.example.first_project.service.BooksServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BooksController {


    private final BooksServices booksServices;

    public BooksController(BooksServices booksServices) {
        this.booksServices = booksServices;
    }


    @PostMapping("/addBooks")
    public Books create(@RequestBody Books books) {
        return booksServices.create(books);
    }

    @GetMapping("/AllBooks")
    public List<Books> allBooks(){
        return booksServices.allBooks();
    }

    @GetMapping("/BooksId")
    public Books booksById(Long id){
        return booksServices.BooksById(id);
    }

    @PutMapping("/UpdateBook")
    public Books updateBooks (Long id, Books books){
        return booksServices.update(id, books);
    }

    @GetMapping("/searchBooks")
    public List<Books> search (String searchBook){
        return booksServices.search(searchBook);
    }
    @DeleteMapping("/DeleteBook")
    public void delete(Long id){
        booksServices.delete(id);
    }

    @PostMapping("/{bookId}/borrow/{sid}")
    public ResponseEntity<Books> borrowBook(@PathVariable Long bookId,@PathVariable Long sid){
        Books borrowedBook = booksServices.borrowBook(bookId, sid);
        if (borrowedBook != null){
            return ResponseEntity.ok(borrowedBook);
        }else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{bookId}/return")
    public ResponseEntity<Books> returnBooks(@PathVariable Long bookId){
        Books returnedBook = booksServices.returnBooks(bookId);
        if (returnedBook != null){
            return ResponseEntity.ok(returnedBook);
        }else {
            return ResponseEntity.badRequest().build();
        }
    }
}
