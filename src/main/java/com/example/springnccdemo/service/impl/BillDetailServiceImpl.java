package com.example.springnccdemo.service.impl;

import com.example.springnccdemo.model.BillDetail;
import com.example.springnccdemo.repository.BillDetailRepository;
import com.example.springnccdemo.service.BillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Component
public class BillDetailServiceImpl implements BillDetailService {
    private  final  BillDetailRepository billDetailRepository;
    @Autowired
    public BillDetailServiceImpl(BillDetailRepository  billDetailRepository ) {
        this . billDetailRepository = billDetailRepository;
    }

    @Override
    public  List < BillDetail >  findAll () {
        return billDetailRepository . findAll ();
    }

    @Override
    public BillDetail findById(int id) {
        return billDetailRepository . getById (id);
    }

    @Override
    public  void  save ( BillDetail  billDetail ) {
        billDetailRepository . save (billDetail);
    }

    @Override
    public  BillDetail  edit ( BillDetail  billDetail ) {
        return null;
    }

    @Override
    public void deleteById(int id) {
        billDetailRepository . deleteById (id);
    }

    @Override
    public List<BillDetail> findBillDetailByBillId(int id) {
        return billDetailRepository.findBillByBill_Id(id);

    }

    @Override
    public Optional<BillDetail> getBillById(int id) {
        return billDetailRepository.findById(id);
    }


//    @Override
//    public Optional<BillDetail> getBillDetailById(Long id) {
//        return billDetailRepository.getById(id);
//    }



//    @Override
//    public  List < BillDetail >  getAllProductInBill ( int  billId ) throws  SQLException {
//        return billDetailRepository . findBillDetailById (billId);
//    }

    @Override
    public long totalMoney(int billId) throws SQLException {
        return billDetailRepository . totalMoney (billId);
    }
}
