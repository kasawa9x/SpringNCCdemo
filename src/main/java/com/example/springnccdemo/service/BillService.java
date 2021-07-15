package com.example.springnccdemo.service;

import com.example.springnccdemo.model.Bill;

import java.util.List;
import java.util.Optional;

public interface BillService {
    public List<Bill> getAllBill();
    public void updateBill(Bill bill);
    public void removeBillById(int id);
    public Optional<Bill> getBillById(int id);
    public List<Bill> findBillByUserId (int userId);

}
