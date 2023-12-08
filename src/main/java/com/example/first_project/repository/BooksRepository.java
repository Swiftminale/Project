package com.example.first_project.repository;

import com.example.first_project.model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository <Books, Long> {



    @Query("select distinct  b from Books b where " +
            "(:searchBook is null or b.bookName = :searchBook or b.Author = :searchBook or cast(b.bookId as string) = :searchBook)")
    List<Books> searchBook(@Param("searchBook") String searchBook);


    Books findByBookName(String criminal);
}
