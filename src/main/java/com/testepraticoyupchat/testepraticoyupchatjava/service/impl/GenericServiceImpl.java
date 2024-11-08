package com.testepraticoyupchat.testepraticoyupchatjava.service.impl;

import com.testepraticoyupchat.testepraticoyupchatjava.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public abstract class GenericServiceImpl <T, ID> implements GenericService<T, ID> {
    @Autowired
    private JpaRepository<T, ID> repository;

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public void removeById(ID id) {
        repository.deleteById(id);
    }

    @Override
    public T getById(ID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public T save(T t) {
        return repository.save(t);
    }
}
