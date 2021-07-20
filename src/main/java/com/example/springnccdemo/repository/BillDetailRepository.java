package com.example.springnccdemo.repository;

import com.example.springnccdemo.model.Bill;
import com.example.springnccdemo.model.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;
@Repository
public interface BillDetailRepository extends JpaRepository<BillDetail, Integer> {
//    @Query(value = "select * from BillDetail b where b.bill.id = ?", nativeQuery = true )
//    List<BillDetail> findBillDetailById(int billId) throws SQLException;
    List<BillDetail> findBillByBill_Id(@Param("bill_id") int bill_id );

    @Query(value = "SELECT SUM(price) FROM bill_detail WHERE bill_id =  ?1", nativeQuery = true)
    long totalMoney(int billId) throws SQLException;
}
