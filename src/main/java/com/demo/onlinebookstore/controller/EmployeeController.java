package com.demo.onlinebookstore.controller;

import com.demo.onlinebookstore.business.EmployeeService;
import com.demo.onlinebookstore.request.EmployeeRequest;
import com.demo.onlinebookstore.response.EmployeeResponse;
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

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmployeeController{

    private final EmployeeService employeeService;
    private final ProjectionFactory projectionFactory;

    @GetMapping
    public List<EmployeeResponse> getAll() {
        return employeeService.getAll().stream().map(x -> projectionFactory.createProjection(EmployeeResponse.class , x)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EmployeeResponse getById(@PathVariable final UUID id) {
        return projectionFactory.createProjection(EmployeeResponse.class , employeeService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeResponse create(@RequestBody @Valid EmployeeRequest employeeRequest, BindingResult result) throws Exception {
        if(result.hasErrors())
            throw new Exception(result.getFieldErrors().toString());
        return projectionFactory.createProjection(EmployeeResponse.class , employeeService.create(employeeRequest));
    }

   @PutMapping("/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable final UUID id,@RequestBody @Valid EmployeeRequest employeeRequest, BindingResult result) throws Exception {
        if(result.hasErrors())
            throw new Exception(result.getFieldErrors().toString());
        employeeService.update(id,employeeRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final UUID id) {
        employeeService.delete(id);
    }
}
