package com.example.springnccdemo.dto;

import lombok.Data;


@Data
public class ProductDTO {
    private Long id;

    private String name;

    private int categoryId;

    private long price;

    private int  quantity;

    private String description;

    private String imageName;

}
