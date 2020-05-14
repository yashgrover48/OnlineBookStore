package com.demo.onlinebookstore.business;

import com.demo.onlinebookstore.entity.Book;
import com.demo.onlinebookstore.repository.BookRepository;
import com.demo.onlinebookstore.request.BookRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookService implements BaseService<Book , BookRequest> {

    private final BookRepository bookRepository;
    private final BookCategoryService bookCategoryService;

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Book> getAllBySearch(String search) {
        return bookRepository.findByNameContaining(search);
    }

    @Override
    @Transactional(readOnly = true)
    public Book getById(UUID id) {
        return bookRepository.findById(id).get();
    }

    @Transactional(readOnly = true)
    public List<Book> getByCategoryId(UUID id) {
        return bookRepository.findByBookCategory(bookCategoryService.getById(id));
    }

    @Override
    @Transactional
    public Book create(BookRequest bookRequest) {
        Book book = Book.builder()
                .name(bookRequest.getName())
                .description(bookRequest.getDescription())
                .imageUrl(bookRequest.getImageUrl())
                .active(true)
                .createdOn(new Date())
                .updatedOn(new Date())
                .sku(bookRequest.getSku())
                .unitPrice(bookRequest.getUnitPrice())
                .bookCategory(bookCategoryService.getById(bookRequest.getBookCategoryId()))
                .unitsInStock(bookRequest.getUnitsInStock()).build();
        return bookRepository.save(book);

    }

    @Override
    @Transactional
    public void update(UUID id, BookRequest bookRequest) {
        Optional<Book> optional = Optional.ofNullable(bookRepository.getOne(id));
        if(!optional.isPresent())
            throw new NoSuchElementException("Book does not exist");
        Book book = optional.get();
        book.setName(bookRequest.getName());
        book.setDescription(bookRequest.getDescription());
        book.setSku(bookRequest.getSku());
        book.setBookCategory(bookCategoryService.getById(bookRequest.getBookCategoryId()));
        book.setUnitPrice(bookRequest.getUnitPrice());
        book.setUnitsInStock(bookRequest.getUnitsInStock());
        book.setUpdatedOn(new Date());
        bookRepository.save(book);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Optional<Book> optional = Optional.ofNullable(bookRepository.getOne(id));
        if(!optional.isPresent())
            throw new NoSuchElementException("Book does not exist");
        Book book = optional.get();
        bookRepository.delete(book);
    }
}
