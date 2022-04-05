package ru.gb.persist;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();
    Product findById(Long id);
    void insert(Product product);
    void update(Product product);
    void delete(Long id);
    long getCount();



}
