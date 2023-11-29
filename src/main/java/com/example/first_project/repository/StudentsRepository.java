package com.example.first_project.repository;

import com.example.first_project.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentsRepository extends JpaRepository<Students, Long> {

//    @Query("SELECT s FROM Students s WHERE " +
//            "LOWER(s.firstname) " + "LIKE LOWER(CONCAT('%', :searchTerm, '%'))" +
//            " OR LOWER(s.lastname) LIKE LOWER(CONCAT('%', :searchTerm, '%'))" +
//            "OR LOWER (s.id) LIKE LOWER (CONCAT('%', :searchTerm, '%'))")

//    @Query("select distinct s from Students s where " +
//    "(:searchingString is null or s.firstname = :searchString or s.lastname = :searchString or cast(s.id as string = :searchString")
//    List<Students> searchStudents(@Param("searchString") String searchString);

    @Query("select distinct s from Students s where " +
            "(:searchString is null or s.firstname = :searchString or s.lastname = :searchString or cast(s.id as string) = :searchString)")
    List<Students> searchStudents(@Param("searchString") String searchString);

}


