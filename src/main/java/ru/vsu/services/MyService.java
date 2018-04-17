package ru.vsu.services;

import java.util.List;

public interface MyService<T> {
    void delete(T obj);

    long insert(T obj);
    void update(T obj);
    List<T> getAll();
}
