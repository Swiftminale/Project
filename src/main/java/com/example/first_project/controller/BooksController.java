package com.example.first_project.controller;

import com.example.first_project.model.Books;
import com.example.first_project.service.BooksServices;
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
    public Books booksById (Long id){
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
}
