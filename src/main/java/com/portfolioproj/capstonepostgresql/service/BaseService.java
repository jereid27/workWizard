package com.portfolioproj.capstonepostgresql.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
    List<T> getAll();
    Optional<T> getById(Long id);
    T save(T entity);
    void delete(Long id);
    List<T> listAll(String keyword);

    //BaseService is an implementation of inheritance and polymorphism.

    //Implementations of this interface provide specific
    // functionality for different types of entities, allowing for polymorphism.

    //This demonstrates the use of a database to create, read, update, and delete data.
}
