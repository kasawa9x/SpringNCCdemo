package com.example.springnccdemo.controller;

import com.example.springnccdemo.dto.BillDTO;
import com.example.springnccdemo.model.*;
import com.example.springnccdemo.repository.UserRepository;
import com.example.springnccdemo.service.BillDetailService;
import com.example.springnccdemo.service.BillService;

import com.example.springnccdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    BillService billService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BillDetailService billDetailService;

    public List<CartItem> cartItems = new ArrayList<>();

    private final ProductService productService;

    @Autowired
    public CartController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/cart")
    public String cart(Model model) {
        long total = 0;
        for (CartItem item : cartItems) {
            total = (total + item.getQuantity() * item.getProduct().getPrice());
        }
        long total1 = 0;
        for (CartItem item : cartItems) {
            total1 = item.getQuantity() * item.getProduct().getPrice();
        }
        model.addAttribute("total1", cartItems.size());
        model.addAttribute("total", total);
        model.addAttribute("carts", cartItems);
        model.addAttribute("cartCount", cartItems.size());
        return "cart";
    }


    @GetMapping("/cart/add-cart")
    public String addToCart(@RequestParam int id, HttpServletRequest request) {
        Product p = productService.getProductById(id).get();
        addToCard (p);
        HttpSession session = request.getSession();
        System.out.println(cartItems.toString());
        session.setAttribute("cart", cartItems);
        return "redirect:/cart";
    }

    @GetMapping("/cart/delete-cart")
    public String deleteCart(@RequestParam int id, HttpServletRequest request) {
        Product p = productService.getProductById(id).get();
        deleteFromCart(p);
        HttpSession session = request.getSession();
        System.out.println(cartItems.size());
        session.setAttribute("cart", cartItems);
        return "redirect:/cart";
    }

    @GetMapping("/cart/remove-cart")
    public String removeCart(@RequestParam int id, HttpServletRequest request) {
        Product p = productService.getProductById(id).get();
        removeFromCart(p);
        HttpSession session = request.getSession();
        System.out.println(cartItems.size());
        session.setAttribute("cart", cartItems);
        return "redirect:/cart";
    }

    @GetMapping("/cart/set-cart")
    public String setCart(@RequestParam(value = "id") int id, @RequestParam(value = "num") int num, HttpServletRequest request) {
        Product p = productService.getProductById(id).get();
        setCart(p, num);
        HttpSession session = request.getSession();
        System.out.println(cartItems.size());
        session.setAttribute("cart", cartItems);
        return "redirect:/";
    }
    @GetMapping("/checkout")
    public String checkout(Model model){
        long total = 0;
        for (CartItem item : cartItems) {
            total = (total + item.getQuantity() * item.getProduct().getPrice());
        }
        model.addAttribute("total", total);
        model.addAttribute("carts", cartItems);
        model.addAttribute("cartCount", cartItems.size());
//        model.addAttribute("cartCount", cartItems.size());
//        long total = 0;
//        for (CartItem item : cartItems) {
//            total = (total + item.getQuantity() * item.getProduct().getPrice());
//        }
//        model.addAttribute("total", total);
        //model.addAttribute("cart", GlobalData.cart);
        model.addAttribute("billDTO", new BillDTO());
        return "checkout";
    } // checkout totalPrice
    @GetMapping("/order")
    public String billget(Model model,@ModelAttribute("billDTO") BillDTO billDTO){
//        model.addAttribute("bills",billService.getBillById(billDTO.getId()));
        return "order";
    }

    @PostMapping("/checkout")
    public String billPost(HttpServletRequest request,Model model, @ModelAttribute("billDTO") BillDTO billDTO,  Principal principal)  {

        Bill bill= new Bill();
        bill.setId(billDTO.getId());
        bill.setName(billDTO.getName());
        bill.setAddr(billDTO.getAddr());
        bill.setCity(billDTO.getCity());
        bill.setPhone(billDTO.getPhone());

        bill.setNote(billDTO.getNote());
        long total = 0;
        for (CartItem item : cartItems) {
            total = (total + item.getQuantity() * item.getProduct().getPrice());
        }

        bill.setPrice(total);
        bill.setUser(userRepository.findUserByEmail(principal.getName()).get());


        billService.updateBill(bill);


//        BillDetail billDetail = new BillDetail();
//        billDetail.setId(billDetailDTO.getId());
//        billDetail.setPrice();
        ArrayList<CartItem> carts = (ArrayList<CartItem>) request.getSession().getAttribute("cart");
        System.out.println(carts);
        BillDetail billDetail =  null ;
        Product product = null;
        for (CartItem item:carts) {
            product = productService.getProductById(item.getProduct().getId()).get();
//            System.out.println(product.toString());
            billDetail = new BillDetail();
            billDetail.setQuantity(item.getQuantity());
            billDetail.setPrice(item.getProduct().getPrice());
            billDetail.setProduct(product);
            billDetail.setBill(bill);

            billDetailService.save(billDetail);
//            System.out.println(billDetail.toString());
        }
//
//        ArrayList<CartItem> carts = (ArrayList<CartItem>) request.getSession().getAttribute("cart");
//        System.out.println(carts);
//        BillDetail billDetail =  null ;
//        Product product = null;
//        for (CartItem item:carts) {
//            product = productService.getProductById(item.getProduct().getId()).get();
//            billDetail = new BillDetail();
//            billDetailService.save(billDetail);
//
//        }




        cartItems.clear();


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


    public String setCart(Product p, int s) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId() == p.getId()) {
                item.setQuantity(s);
                return "cart";
            }
        }
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(s);
        cartItem.setProduct(p);
        cartItems.add(cartItem);
        return "cart";
    }

    public String addToCard(Product p) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId() == p.getId()) {
                item.setQuantity(item.getQuantity() + 1);
                return "cart";
            }
        }
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(1);
        cartItem.setProduct(p);
        cartItems.add(cartItem);
        return "cart";

    }

    public String deleteFromCart(Product p) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId() == p.getId() && item.getQuantity() > 1) {
                item.setQuantity(item.getQuantity() - 1);
                return "cart";
            } else if (item.getProduct().getId() == p.getId() && item.getQuantity() == 0) {
                cartItems.remove(item);
                return "cart";
            }
        }
        return "cart";
    }

    private String removeFromCart(Product p) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId() == p.getId()) {
                cartItems.remove(item);
                return "cart";
            }
        }
        return "cart";
    }

}