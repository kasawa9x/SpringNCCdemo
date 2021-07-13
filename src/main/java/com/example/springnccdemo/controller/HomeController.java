package com.example.springnccdemo.controller;


import com.example.springnccdemo.global.GlobalData;
import com.example.springnccdemo.model.User;
import com.example.springnccdemo.service.CategoryService;
import com.example.springnccdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Controller
public class HomeController {
    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;


    @GetMapping("/error")
    public String erorPage(){
        return "error";
    }//page error
    @GetMapping({"/", "/home"})
    public String home(Model model){
//        String userName = principal.getName();
//                    model.addAttribute("username",userName);
//        if (principal instanceof UserDetails) {
//            String username = ((UserDetails) principal).getUsername();
//            System.out.printf(username);
//            model.addAttribute("username",username);
//        } else {
//            String username = principal.toString();
//        }

        model.addAttribute("cartCount", GlobalData.cart.size());
        return "index";
    } //index

    @GetMapping("/shop")
    public String shop(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProduct());
        return "shop";
    } //view All Products

    @GetMapping("/shop/category/{id}")
    public String shopByCat(@PathVariable int id, Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("categories", categoryService.getAllCategory());
        model.addAttribute("products", productService.getAllProductByCategoryId(id));
        return "shop";
    } //view Products By Category

    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(@PathVariable long id, Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        model.addAttribute("product", productService.getProductById(id).get());
        return "viewProduct";
    } //view product Details


}
