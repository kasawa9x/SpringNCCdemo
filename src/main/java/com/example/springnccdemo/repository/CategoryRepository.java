package com.example.springnccdemo.repository;


import com.example.springnccdemo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
