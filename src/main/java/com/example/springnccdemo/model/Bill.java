package com.example.springnccdemo.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bill_id")
    private int id;
    private String name;
    private String addr;
    private String city;
    private String phone;
    private String email;
    private String note;
    private long price;
}