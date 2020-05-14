package com.demo.onlinebookstore.business;

import javax.persistence.Entity;
import java.util.List;
import java.util.UUID;

public interface BaseService<Entity, Request> {

    List<Entity> getAll();
    Entity getById(final UUID id);
    Entity create(Request request);
    void update(final UUID id, Request request);
    void delete(final UUID id);
}

