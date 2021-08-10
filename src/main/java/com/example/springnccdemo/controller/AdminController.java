package com.example.springnccdemo.controller;


import com.example.springnccdemo.dto.ProductDTO;
import com.example.springnccdemo.dto.ProductMapper;
import com.example.springnccdemo.model.Category;
import com.example.springnccdemo.model.Product;
import com.example.springnccdemo.service.BillDetailService;
import com.example.springnccdemo.service.BillService;
import com.example.springnccdemo.service.CategoryService;
import com.example.springnccdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class AdminController {
    public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/productImages";

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;
    @Autowired
    BillService billService;
    @Autowired
    BillDetailService billDetailService;
    @Autowired
    ProductMapper productMapper;


    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome";
    }//page admin home

    //Categories session
    @GetMapping("/admin/categories")
    public String getCat(Model model){
        model.addAttribute("categories", categoryService.getAllCategory());
        return "categories";
    }//view all categories
    @GetMapping("/admin/history")
    public String getOrder(Model model){
        model.addAttribute("bills", billService.getAllBillDTO());
        return "history";
    }//view all order

    @GetMapping("/admin/categories/add")
    public String getCatAdd(Model model){
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }//form add new category

    @PostMapping("/admin/categories/add")
    public String postCatAdd(@ModelAttribute("category") Category category){
        categoryService.updateCategory(category);
        return "redirect:/admin/categories";
    }//form add new category > do add

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCat(@PathVariable int id){
        categoryService.removeCategoryById(id);
        return "redirect:/admin/categories";
    }//delete 1 category

    @GetMapping("/admin/categories/update/{id}")
    public String updateCat(@PathVariable int id, Model model){
        Optional<Category> category = categoryService.getCategoryById(id);
        if(category.isPresent()){
            model.addAttribute("category", category.get());
            return "categoriesAdd";
        }else {
            return "error";
        }
    }//form edit category, fill old data into form

    //Products session
    @GetMapping("/admin/products")
    public String getPro(Model model){
        model.addAttribute("productDTOS", productService.getAllProductDTO());
//        model.addAttribute("categories", categoryService.ge());
        return "products";
    }//view all products

    @GetMapping("/admin/products/add")
    public String getProAdd(Model model){
        model.addAttribute("productDTO", new ProductDTO());
        model.addAttribute("categories", categoryService.getAllCategory());
        return "productsAdd";
    }// form add new product

    @PostMapping("/admin/products/add")
    public String postProAdd(@ModelAttribute("productDTO") ProductDTO productDTO,
                             @RequestParam("productImage") MultipartFile fileProductImage,
                             @RequestParam("imgName") String imgName) throws IOException {
        Product product = productMapper.productDTOToProduct(productDTO);


        //convert dto > entity
//        Product product = new Product();
//        product.setId(productDTO.getId());
//        product.setName(productDTO.getName());
//        product.setCategory(categoryService.getCategoryById(productDTO.getCategory().getId()).get());
//        product.setPrice(productDTO.getPrice());
//        product.setQuantity(productDTO.getQuantity());
//        product.setDescription(productDTO.getDescription());
        String imageUUID;
        if(!fileProductImage.isEmpty()){
            imageUUID = fileProductImage.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, fileProductImage.getBytes());
        }else {
            imageUUID = imgName;
        }//save image
        product.setImageName(imageUUID);
//
//        productService.updateProduct(product);

        productService.updateProduct(product);
        return "redirect:/admin/products";
    }//form add new product > do add

    @GetMapping("/admin/products/delete/{id}")
    public String deletePro(@PathVariable long id){
        productService.removeProductById(id);
        return "redirect:/admin/products";
    }//delete 1 product

    @GetMapping("/admin/products/update/{id}")
    public String updatePro(@PathVariable long id, Model model){
        Optional<Product> opProduct = productService.getProductById(id);
        if (opProduct.isPresent()){
            Product product = opProduct.get();
            //convert entity > dto
            ProductDTO productDTO = productMapper.productToProductDTO(product);

//            ProductDTO productDTO = new ProductDTO();
//            productDTO.setId(product.getId());
//            productDTO.setName(product.getName());
//            productDTO.setCategoryId(product.getCategory().getId());
//            productDTO.setPrice(product.getPrice());
//            productDTO.setQuantity(product.getQuantity());
//            productDTO.setDescription(product.getDescription());
//            productDTO.setImageName(product.getImageName());

            model.addAttribute("productDTO", productDTO);
            model.addAttribute("categories", categoryService.getAllCategory());
            return "productsAdd";
        }else {
            return "404";
        }

    }//form edit product, fill old data into form
    @GetMapping("/admin/billdetail/{id}")
    public String billDetail(@PathVariable Integer id, Model model){

        model.addAttribute("billdetail", billDetailService.findBillDetailByBillId(id));
        return "billdetail";
    } //view bill Details
//    @GetMapping("/sendemo")
//    public String senDemo(@PathVariable Integer id, Model model){
//
//
//        return "billdetail";
//    } //view bill Details
}
