package com.example.springnccdemo.dto;

import com.example.springnccdemo.model.Bill;
import com.example.springnccdemo.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
@Mapper(componentModel = "spring")
public interface BillMapper {
    BillMapper INSTANCE = Mappers.getMapper(BillMapper.class);
    BillDTO billToBillDTO (Bill bill);
    Bill billDTOToBill(BillDTO billDTO);
    List<BillDTO> billsToBillDTOS(List<Bill> bills);
}
