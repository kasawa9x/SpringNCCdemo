package com.example.springnccdemo.service;

import com.example.springnccdemo.model.Bill;
import com.example.springnccdemo.model.BillDetail;
import com.example.springnccdemo.model.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BillDetailService {

    List<BillDetail> findAll();
    BillDetail findById(int id);
    void save(BillDetail t);
    BillDetail  edit ( BillDetail  t );
    void deleteById(int id);
    List<BillDetail> findBillDetailByBillId (int id);
    Optional<BillDetail> getBillById(int id);

    long totalMoney(int billId) throws SQLException;
}
