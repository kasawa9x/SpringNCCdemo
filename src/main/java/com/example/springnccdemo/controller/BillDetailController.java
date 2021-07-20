package com.example.springnccdemo.controller;

import com.example.springnccdemo.service.BillDetailService;
import com.example.springnccdemo.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.sql.SQLException;

@Controller
public class BillDetailController {
    @Autowired
    BillDetailService billDetailService;
//
//    @GetMapping("/billdetail")
//    public String historyOrder(Model model){
//        int bill_íd = billDetailService.getAllProductInBill();
//        System.out.println(user_íd);
//        model.addAttribute("bills", billService.findBillByUserId(user_íd));
//        return "billdetail";
//    }
    @GetMapping("/billdetail/{id}")
    public String billDetail(@PathVariable Integer id, Model model) throws SQLException {
        long total = billDetailService.totalMoney(id);
        System.out.println(total);
        model.addAttribute("total", total);
//        model.addAttribute("billdetail1", billDetailService.getBillById(id).get());

        model.addAttribute("billdetail", billDetailService.findBillDetailByBillId(id));
        return "billdetail";
    } //view bill Details


}
