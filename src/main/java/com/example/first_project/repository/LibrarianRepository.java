package com.example.first_project.repository;

import com.example.first_project.model.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian, Long> {

    @Query("select distinct l from Librarian l where " +
    "(:seachString is null or l.firstname = :searchString or l.lastname = : searchString or cast(l.Lid as string) = :searchString)")

    List<Librarian> searchLibrarian(@Param(("searchString")) String searchLibrarian);

    Librarian findByfirstname(String mixsug);
}
