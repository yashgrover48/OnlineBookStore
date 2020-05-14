package com.demo.onlinebookstore.repository;

import com.demo.onlinebookstore.entity.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookCategoryRepository extends JpaRepository<BookCategory , UUID> {
}
