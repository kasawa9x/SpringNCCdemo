package com.example.springnccdemo.service.impl;

import com.example.springnccdemo.dto.BillDTO;
import com.example.springnccdemo.dto.BillMapper;
import com.example.springnccdemo.dto.ProductDTO;
import com.example.springnccdemo.model.Bill;
import com.example.springnccdemo.repository.BillRepository;
import com.example.springnccdemo.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BillServiceImpl implements BillService {
    @Autowired
    BillRepository billRepository;
    @Autowired
    BillMapper billMapper;
    @Override
    public List<Bill> getAllBill() {
        return billRepository.findAll();
    }

    @Override
    public List<BillDTO> getAllBillDTO() {
        List<BillDTO> dtos = billMapper.billsToBillDTOS(billRepository.findAll());
        return dtos;
    }

    @Override
    public void updateBill(Bill bill) {
         billRepository.save(bill);
    }

    @Override
    public void removeBillById(int id) {
        billRepository.deleteById(id);
    }

    @Override
    public Optional<Bill> getBillById(int id) {
        return billRepository.findById(id);
    }

    @Override
    public List<BillDTO> findBillByUserId(int user_id) {
        List<BillDTO> dtos = billMapper.billsToBillDTOS(billRepository.findBillByUserId(user_id));
        return dtos;
    }


}
