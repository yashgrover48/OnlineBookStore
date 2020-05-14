package com.demo.onlinebookstore.business;

import com.demo.onlinebookstore.entity.BookCategory;
import com.demo.onlinebookstore.repository.BookCategoryRepository;
import com.demo.onlinebookstore.request.BookCategoryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookCategoryService implements BaseService<BookCategory , BookCategoryRequest> {

    private final BookCategoryRepository bookCategoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BookCategory> getAll() {
        return bookCategoryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public BookCategory getById(UUID id) {
        return bookCategoryRepository.findById(id).get();
    }

    @Override
    @Transactional
    public BookCategory create(BookCategoryRequest bookCategoryRequest) {
        BookCategory bookCategory = BookCategory.builder()
                .categoryName(bookCategoryRequest.getCategoryName()).build();
        return bookCategoryRepository.save(bookCategory);
    }

    @Override
    @Transactional
    public void update(UUID id, BookCategoryRequest bookCategoryRequest) {
        Optional<BookCategory> optional = Optional.ofNullable(bookCategoryRepository.getOne(id));
        BookCategory bookCategory = optional.get();
        bookCategory.setCategoryName(bookCategoryRequest.getCategoryName());
        bookCategoryRepository.save(bookCategory);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Optional<BookCategory> optional = Optional.ofNullable(bookCategoryRepository.getOne(id));
        BookCategory bookCategory = optional.get();
        bookCategoryRepository.delete(bookCategory);
    }
}
