package com.demo.onlinebookstore.controller;


import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

public interface BaseController<Request, Response> {

    List<Response> getAll();

    Response getById(final UUID id);

    Response create(@Valid final Request request, BindingResult result) throws Exception;

    void update(final UUID id, @Valid final Request request, BindingResult result) throws Exception;

    void delete(final UUID id);

}
