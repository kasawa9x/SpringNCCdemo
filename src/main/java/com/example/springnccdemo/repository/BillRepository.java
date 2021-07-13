package com.example.springnccdemo.repository;

import com.example.springnccdemo.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill,Integer> {
}
