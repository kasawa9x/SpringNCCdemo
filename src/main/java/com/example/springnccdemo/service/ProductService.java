package com.example.springnccdemo.service;


import com.example.springnccdemo.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    public List<Product> getAllProduct();
    public void updateProduct(Product product);
    public void removeProductById(long id);
    public Optional<Product> getProductById(long id);
    public List<Product> getAllProductByCategoryId(int id);
}
