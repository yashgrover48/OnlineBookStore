package com.demo.onlinebookstore.controller;

import com.demo.onlinebookstore.business.BookCategoryService;
import com.demo.onlinebookstore.request.BookCategoryRequest;
import com.demo.onlinebookstore.response.BookCategoryResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

//@CrossOrigin(origins = "http://localhost:4200", allowedHeaders ="true" , allowCredentials = "true")
@RestController
@RequestMapping("/bookCategory")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookCategoryController implements BaseController<BookCategoryRequest , BookCategoryResponse> {

    private final BookCategoryService bookCategoryService;
    private final ProjectionFactory projectionFactory;
    

    @CrossOrigin
    @Override
    @GetMapping("/all")
    public List<BookCategoryResponse> getAll() {
        return bookCategoryService.getAll().stream().map(x -> projectionFactory.createProjection(BookCategoryResponse.class , x)).collect(Collectors.toList());
    }

    @CrossOrigin
    @Override
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookCategoryResponse getById(@PathVariable final UUID id) {
        return projectionFactory.createProjection(BookCategoryResponse.class , bookCategoryService.getById(id));
    }

    @Override
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public BookCategoryResponse create(@RequestBody @Valid BookCategoryRequest bookCategoryRequest, BindingResult result) throws Exception {
        if(result.hasErrors())
            throw new Exception(result.getFieldErrors().toString());
        return projectionFactory.createProjection(BookCategoryResponse.class , bookCategoryService.create(bookCategoryRequest));
    }

    @Override
    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable final UUID id, @RequestBody @Valid BookCategoryRequest bookCategoryRequest, BindingResult result) throws Exception {
        if(result.hasErrors())
            throw new Exception(result.getFieldErrors().toString());
        bookCategoryService.update(id , bookCategoryRequest);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final UUID id) {
        bookCategoryService.delete(id);
    }
}
