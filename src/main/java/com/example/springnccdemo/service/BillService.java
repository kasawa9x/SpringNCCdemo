package com.example.springnccdemo.service;

import com.example.springnccdemo.model.Bill;

import java.util.List;
import java.util.Optional;

public interface BillService {
     List<Bill> getAllBill();
     void updateBill(Bill bill);
     void removeBillById(int id);
     Optional<Bill> getBillById(int id);
     List<Bill> findBillByUserId (int userId);

}
