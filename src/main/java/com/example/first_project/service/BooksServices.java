package com.example.first_project.service;

import com.example.first_project.model.Books;
import com.example.first_project.repository.BooksRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksServices {

  private final BooksRepository booksRepository;

    public BooksServices(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
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
}
