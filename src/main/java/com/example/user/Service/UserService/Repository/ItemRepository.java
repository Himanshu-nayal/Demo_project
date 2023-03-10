package com.example.user.Service.UserService.Repository;

import com.example.user.Service.UserService.Entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item,Long> {

    List<Item> findByItemNumberContainingIgnoreCase(String itemNumber);
    List<Item> findByItemNameContainingIgnoreCase(String itemName);
}
