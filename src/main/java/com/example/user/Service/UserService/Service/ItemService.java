package com.example.user.Service.UserService.Service;

import com.example.user.Service.UserService.Dto.ItemDto;
import com.example.user.Service.UserService.Entity.Item;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ItemService {
    Item createItem(ItemDto itemDto);

    Item updateItem(Long id, ItemDto itemDto);

    void deleteItem(Long id);

    List<Item> searchItems(String itemNumber, String itemName);
}
