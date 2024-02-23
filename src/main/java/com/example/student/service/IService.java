package com.example.student.service;

import java.util.List;

public interface IService<T> {
    List<T> findAll();
    void add(T t);
    T findById(int id);
    void update(int id,T t);
    void delete(int id);
}
