package com.testepraticoyupchat.testepraticoyupchatjava.service;

import java.util.List;

public interface GenericService<T, ID> {
    public List<T> getAll();
    public void removeById(ID id);
    public T getById(ID id);
    public T save(T t);
}
