package com.example.user.Service.UserService.Dto;

import lombok.Data;

import java.time.LocalTime;

@Data
public class StoreDto {
    private String storeNo;

    private String storeName;

    private String storePattern;

    private String storeLocation;

    private String pinCode;

    private LocalTime openingTime;

    private LocalTime closingTime;
    private Long itemId;
}
