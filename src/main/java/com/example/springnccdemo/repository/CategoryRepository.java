package com.example.springnccdemo.repository;


import com.example.springnccdemo.model.BillDetail;
import com.example.springnccdemo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
