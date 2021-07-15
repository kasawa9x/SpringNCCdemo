package com.example.springnccdemo.dto;

import lombok.Data;

@Data
public class BillDTO {
    private int id;
    private String name;
    private String addr;
    private String city;
    private String phone;
    private String email;
    private String note;
    private long price;
    private String userName;
}

