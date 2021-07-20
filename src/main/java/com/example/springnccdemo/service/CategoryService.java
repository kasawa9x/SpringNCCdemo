package com.example.springnccdemo.service;



import com.example.springnccdemo.model.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public interface CategoryService {

     List<Category> getAllCategory();

     void updateCategory(Category category);

     void removeCategoryById(int id);

     Optional<Category> getCategoryById(int id);

}
