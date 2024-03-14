package com.portfolioproj.capstonepostgresql.service;

import java.util.List;
import java.util.Optional;

public interface BaseService<T> {
    List<T> getAll();
    Optional<T> getById(Long id);
    T save(T entity);
    void delete(Long id);
    List<T> listAll(String keyword);
}
