package ru.gb.persist;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ProductRepositoryImpl implements ProductRepository{

    private final Map<Long, Product> productMap = new ConcurrentHashMap<>();
    private final AtomicLong identity = new AtomicLong(0);

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(productMap.values());
    }

    @Override
    public Product findById(Long id) {
        return productMap.get(id);
    }

    @Override
    public void insert(Product product) {
        Long id = identity.incrementAndGet();
        product.setId(id);
        productMap.put(id, product);
    }

    @Override
    public void update(Product product) {
        if(product.getId() == null) {
            Long id = identity.incrementAndGet();
            product.setId(id);
        }
        productMap.put(product.getId(), product);
    }

    @Override
    public void delete(Long id) {
        productMap.remove(id);
    }

    @Override
    public long getCount() {
        return 0;
    }
}
