package com.shaw.kratos.dao;

public interface IBaseDAO<T> {

    T getById(Long id);

    void add(T t);

    int update(T t);

    int deleteById(Long id);
}
