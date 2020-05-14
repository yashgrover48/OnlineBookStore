package com.demo.onlinebookstore.repository;

import com.demo.onlinebookstore.entity.Book;
import com.demo.onlinebookstore.entity.BookCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book , UUID> {

    List<Book> findByBookCategory(BookCategory bookCategory);

    List<Book> findByNameContaining(@Param("name") String name);
}
