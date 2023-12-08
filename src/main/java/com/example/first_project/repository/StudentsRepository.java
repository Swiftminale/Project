package com.example.first_project.repository;

import com.example.first_project.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Long> {

    @Query("select distinct s from Students s where " +
            "(:searchString is null or s.firstname = :searchString or s.lastname = :searchString or cast(s.sid as string) = :searchString)")
    List<Students> searchStudents(@Param("searchString") String searchString);

    Students findByFirstname(String minale);
}


