package com.example.first_project.controller;

import com.example.first_project.model.Librarian;
import com.example.first_project.repository.LibrarianRepository;
import com.example.first_project.service.LibrarianService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/api/Librarian")
public class LibrarianController {

    private final LibrarianService librarianService;
    private final LibrarianRepository librarianRepository;
    public LibrarianController(LibrarianService librarianService, LibrarianRepository librarianRepository) {
        this.librarianService = librarianService;
        this.librarianRepository = librarianRepository;
    }
    @PostMapping("/adLibrarian")
    public Librarian addLibrarian(Librarian librarian){
        return librarianService.addLibrarian(librarian);
    }
    @GetMapping("/AllLibrarians")
    public List<Librarian> findAllLibrarians(){
        return librarianService.findAllLibrarians();
    }
    @GetMapping("/findById")
    public  Librarian findById(Long Lid){
        return librarianService.findById(Lid);
    }
    @PutMapping("/update")
    public Librarian Update(Long Lid, Librarian librarian){
        return librarianService.update(Lid, librarian);
    }

    @GetMapping("/search")
    public List<Librarian> search(String searchLibraian){
        return librarianService.search(searchLibraian);
    }
    @DeleteMapping("/Delete")
    public void delete(Long Lid){
        librarianService.delete(Lid);
    }

    }

