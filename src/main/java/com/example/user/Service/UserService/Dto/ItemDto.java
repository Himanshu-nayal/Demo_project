package com.example.user.Service.UserService.Dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ItemDto {
    private List<Long> storeId;
    private String itemNumber;
    private String itemName;
    private String itemType;
    private String storeType;
    private Double itemPrice;
    private LocalDate itemStartDate;
    private LocalDate itemEndDate;
    private String supplierName;

    public ItemDto() {
    }
}
