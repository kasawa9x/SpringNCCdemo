package com.example.springnccdemo.controller;

import com.example.springnccdemo.dto.BillDTO;
import com.example.springnccdemo.global.GlobalData;
import com.example.springnccdemo.model.*;
import com.example.springnccdemo.repository.UserRepository;
import com.example.springnccdemo.service.BillService;
import com.example.springnccdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CartController {
    @Autowired
    ProductService productService;
    @Autowired
    BillService billService;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/cart")
    public String cartGet(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("total", GlobalData.cart.stream().mapToLong(Product::getPrice).sum());
        model.addAttribute("cart", GlobalData.cart);

        return "cart";
    }//page cart

    @GetMapping("/addToCart/{id}")
    public String addToCart(@PathVariable int id){
        GlobalData.cart.add(productService.getProductById(id).get());
        return "redirect:/shop";
    }//click add from page viewProduct

    @GetMapping("/cart/removeItem/{index}")
    public String cartItemRemove(@PathVariable int index){
        GlobalData.cart.remove(index);
        return "redirect:/cart";
    } // delete 1 product

    @GetMapping("/checkout")
    public String checkout(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("total", GlobalData.cart.stream().mapToLong(Product::getPrice).sum());
        //model.addAttribute("cart", GlobalData.cart);
        model.addAttribute("billDTO", new BillDTO());
        return "checkout";
    } // checkout totalPrice
    @GetMapping("/order")
    public String billget(Model model,@ModelAttribute("billDTO") BillDTO billDTO){
        model.addAttribute("bills",billService.getBillById(billDTO.getId()));
        return "order";
    }

    @PostMapping("/checkout")
    public String billPost(Model model, @ModelAttribute("billDTO") BillDTO billDTO, Principal principal)  {
        Bill bill= new Bill();
        bill.setId(billDTO.getId());
        bill.setName(billDTO.getName());
        bill.setAddr(billDTO.getAddr());
        bill.setCity(billDTO.getCity());
        bill.setPhone(billDTO.getPhone());
        bill.setEmail(billDTO.getEmail());
        bill.setNote(billDTO.getNote());
        bill.setPrice(GlobalData.cart.stream().mapToLong(Product::getPrice).sum());
        bill.setUser(userRepository.findUserByEmail(principal.getName()).get());


        billService.updateBill(bill);
        GlobalData.cart.clear();
        model.addAttribute("order",GlobalData.order);

    return "order";
    }//after register success
//    @PostMapping("/order")
//    public String billPost(Model model,@ModelAttribute("billDTO") BillDTO billDTO){
//        model.addAttribute("bills",billService.getBillById(billDTO.getId()));
//        return "order";
//    }
    @GetMapping("/history")
    public String historyOrder(Model model, Principal principal){
        int user_íd = userRepository.findUserByEmail(principal.getName()).get().getId();
        System.out.println(user_íd);
        model.addAttribute("bills", billService.findBillByUserId(user_íd));
        return "history";
    }
}
