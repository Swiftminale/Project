package com.example.first_project.service;

import com.example.first_project.model.Books;
import com.example.first_project.model.Students;
import com.example.first_project.repository.BooksRepository;
import com.example.first_project.repository.StudentsRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class BooksServices {

  private final BooksRepository booksRepository;
  private final StudentsRepository studentsRepository;

    public BooksServices(BooksRepository booksRepository, StudentsRepository studentsRepository) {
        this.booksRepository = booksRepository;
        this.studentsRepository = studentsRepository;
    }



    public Books create(Books books){
        return booksRepository.save(books);
    }

    public List<Books> allBooks(){
        return booksRepository.findAll();
    }


    public Books BooksById(Long id){
        return booksRepository.findById(id).orElseThrow(()->new EntityNotFoundException("No such book by the id " + id));
    }

    public Books update(Long id, Books books){
        Books books1 = booksRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("no such book by the id "+ id));
        if (books1 != null){
            books1.setBookName(books.getBookName());
            books1.setAuthor(books.getAuthor());
            books1.setGenre(books.getGenre());
            booksRepository.save(books1);
        }
        return null;
    }

    public List<Books> search (String searchBook){
        return booksRepository.searchBook(searchBook);
    }

    public void delete(Long id){
        Books books = booksRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("no such book with the id " + id));
        booksRepository.delete(books);
    }

    public Books borrowBook(Long bookId, Long sid){
        Books books = booksRepository.findById(bookId).orElseThrow(()->new EntityNotFoundException("error" + sid));
        Students students = studentsRepository.findById(sid).orElseThrow(()-> new EntityNotFoundException("error" + sid));

        if (books != null && !books.isBorrowed() && students != null){
            books.setBorrowedBy(students);
            books.setBorrowed(true);
            return booksRepository.save(books);
        }
        return books;

    }


    public Books returnBooks (Long bookId){
        Books books = booksRepository.findById(bookId).orElseThrow(null);
        if (books != null && books.isBorrowed()) {
            books.setBorrowedBy(null);
            books.setBorrowed(false);
            return booksRepository.save(books);
        }
        return books;
        }


    @PostConstruct
    public void initBooks(){
        Books b = booksRepository.findByBookName("criminal");
        if (b == null) {
            Books books = new Books();
            books.setBookName("criminal");
            books.setGenre("crime");
            books.setAuthor("Mastewal");
            booksRepository.save(books);
        }
        }
    }


