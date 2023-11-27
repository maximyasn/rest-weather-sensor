package com.maximyasn.restweathersensor.service;

import java.util.List;
import java.util.Optional;

public interface ServiceInterface<T> {
    List<T> findAll();

    Optional<T> findById(Integer id);

    void save(T t);

}
