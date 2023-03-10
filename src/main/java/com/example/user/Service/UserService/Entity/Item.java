package com.example.user.Service.UserService.Entity;

import com.example.user.Service.UserService.Enums.ItemType;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_number")
    private String itemNumber;

    @Column(name = "item_name")
    private String itemName;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_type")
    private ItemType itemType;

    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Store> stores;

    @Column(name = "item_price")
    private Double itemPrice;

    @Column(name = "item_start_date")
    private LocalDate itemStartDate;

    @Column(name = "item_end_date")
    private LocalDate itemEndDate;

    @Column(name = "supplier_name")
    private String supplierName;
}
