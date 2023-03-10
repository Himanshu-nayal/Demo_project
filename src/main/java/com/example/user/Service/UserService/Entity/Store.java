package com.example.user.Service.UserService.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "store_no", unique = true, nullable = false)
    private String storeNo;

    @Column(name = "store_name", nullable = false)
    private String storeName;

    @Column(name = "store_pattern", unique = true, nullable = false)
    private String storePattern;

    @Column(name = "store_location", nullable = false)
    private String storeLocation;

    @Column(name = "pin_code", nullable = false)
    private String pinCode;

    @Column(name = "opening_time", nullable = false)
    private LocalTime openingTime;

    @Column(name = "closing_time", nullable = false)
    private LocalTime closingTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private List<Item> item;

}
