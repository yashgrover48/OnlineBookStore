package com.demo.onlinebookstore.controller;

import com.demo.onlinebookstore.business.BookService;
import com.demo.onlinebookstore.repository.BookRepository;
import com.demo.onlinebookstore.request.BookRequest;
import com.demo.onlinebookstore.response.BookResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/book")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookController implements BaseController<BookRequest , BookResponse>{

    private final BookService bookService;
    private final ProjectionFactory projectionFactory;

    //@CrossOrigin
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    @Override
    public List<BookResponse> getAll() {
        return bookService.getAll().stream().map(x -> projectionFactory.createProjection(BookResponse.class , x)).collect(Collectors.toList());
    }

    //@CrossOrigin
    @GetMapping("/{id}")
    @Override
    public BookResponse getById(@PathVariable final UUID id) {
        return projectionFactory.createProjection(BookResponse.class , bookService.getById(id));
    }

    @CrossOrigin
    @GetMapping("/search")
    public List<BookResponse> searchByName(@RequestParam(name = "search",required = false) final String search) {
        return bookService.getAllBySearch(search).stream()
                .map(x -> projectionFactory.createProjection(BookResponse.class, x)).collect(Collectors.toList());
        }

    @CrossOrigin
    @GetMapping("/category/{id}")
    public List<BookResponse> getByCategoryId(@PathVariable final UUID id) {
        return bookService.getByCategoryId(id).stream().map(x -> projectionFactory.createProjection(BookResponse.class,x)).collect(Collectors.toList());
    }

    @PostMapping("/create")
    @Override
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponse create(@RequestBody @Valid BookRequest bookRequest, BindingResult result) throws Exception {
        if(result.hasErrors())
            throw new Exception(result.getFieldErrors().toString());
        return projectionFactory.createProjection(BookResponse.class , bookService.create(bookRequest));
    }

    @Override
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable final UUID id,@RequestBody @Valid BookRequest bookRequest, BindingResult result) throws Exception {
        if(result.hasErrors())
            throw new Exception(result.getFieldErrors().toString());
        bookService.update(id , bookRequest);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final UUID id) {
        bookService.delete(id);
    }
}
