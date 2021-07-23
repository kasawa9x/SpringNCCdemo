package com.example.springnccdemo.dto;

import com.example.springnccdemo.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    ProductDTO productToProductDTO (Product product);
    Product productDTOToProduct(ProductDTO productDTO);
    List<ProductDTO> productsToProductDTOS( List<Product> products);
}
