package com.example.first_project.service;

import com.example.first_project.model.Gender;
import com.example.first_project.model.Librarian;
import com.example.first_project.repository.LibrarianRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class LibrarianService {

    private final LibrarianRepository librarianRepository;

    public LibrarianService(LibrarianRepository librarianRepository) {
        this.librarianRepository = librarianRepository;
    }


    public Librarian addLibrarian(Librarian librarian){
        return librarianRepository.save(librarian);
    }

    public List<Librarian> findAllLibrarians(){
        return  librarianRepository.findAll();
    }

    public Librarian findById(Long Lid){
        return librarianRepository.findById(Lid).orElseThrow(()-> new EntityNotFoundException("No such Librariyan by the id " +Lid));
        }
    public Librarian update(Long Lid, Librarian librarian){
        Librarian librarian1 = librarianRepository.findById(Lid).orElseThrow(()-> new EntityNotFoundException("No Such Librarian with the id " + Lid));
        if (librarian1 != null){
            librarian1.setFirstname(librarian.getFirstname());
            librarian1.setLastname(librarian.getLastname());
            librarian1.setGender(librarian.getGender());
            librarianRepository.save(librarian1);
        }
        return null;
    }

    public List<Librarian> search(String searchLibrarian){
        return librarianRepository.searchLibrarian(searchLibrarian);
    }


    public void delete(Long Lid){
        Librarian librarian = librarianRepository.findById(Lid).orElseThrow(()-> new EntityNotFoundException("There is No Librarian By the ID " + Lid));
        librarianRepository.delete(librarian);
    }

    @PostConstruct
    public void initLibrarians(){
        Librarian l = librarianRepository.findByfirstname("Mixsug");
        if (l == null) {
            Librarian librarian = new Librarian();
            librarian.setFirstname("Mixsun");
            librarian.setLastname("Fetene");
            librarian.setGender(Gender.MALE);
            librarianRepository.save(librarian);
        }
    }
}
