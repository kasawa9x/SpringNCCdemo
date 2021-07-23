package com.example.springnccdemo.service;


import com.example.springnccdemo.dto.ProductDTO;
import com.example.springnccdemo.model.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
     List<Product> getAllProduct();
     List<ProductDTO> getAllProductDTO();
     void updateProduct(Product product);
     void removeProductById(long id);
     Optional<Product> getProductById(long id);
     List<Product> getAllProductByCategoryId(int id);
//     List<Product> getProductById(int id);

}
