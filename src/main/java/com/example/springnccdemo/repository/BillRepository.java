package com.example.springnccdemo.repository;

import com.example.springnccdemo.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill,Integer> {
    @Query(value = "SELECT * FROM bill WHERE user_id = ?1" , nativeQuery = true)
    List<Bill> findBillByUserId(@Param("user_id") int user_id );
}
