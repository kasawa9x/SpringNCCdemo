package com.example.springnccdemo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;

    private long price;

    private int  quantity;

    private String description;

    private String imageName;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "billdetail_id", referencedColumnName = "billdetail_id")
//    private BillDetail billDetail;

}//create table mapping trong db
