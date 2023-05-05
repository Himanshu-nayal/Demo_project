package com.example.user.Service.UserService.Controller;

import com.example.user.Service.UserService.Dto.ItemDto;
import com.example.user.Service.UserService.Entity.Item;
import com.example.user.Service.UserService.Entity.Store;
import com.example.user.Service.UserService.Enums.ItemType;
import com.example.user.Service.UserService.Exception.ItemNotFoundException;
import com.example.user.Service.UserService.Repository.ItemRepository;
import com.example.user.Service.UserService.Repository.StoreRepository;
import com.example.user.Service.UserService.Service.ItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/item")
public class ItemController {
    private final ItemService itemService;
    private final ItemRepository itemRepository;
    private final StoreRepository storeRepository;

    public ItemController(ItemService itemService,
                          ItemRepository itemRepository, StoreRepository storeRepository) {
        this.itemService = itemService;

        this.itemRepository = itemRepository;
        this.storeRepository = storeRepository;
    }

    @PostMapping("/")
    public ResponseEntity<ItemDto> createItems(@RequestBody ItemDto itemDto) {
        try {
            Item item = mapItemDTOToItem(itemDto);
            Item itemCreated = itemService.createItem(itemDto);
            ItemDto createdItemDTO = mapItemToItemDTO(itemCreated);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdItemDTO);
        } catch (ItemNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/{ItemId}")
    public ResponseEntity<ItemDto> createItems(@PathVariable("ItemId") Long id, @RequestBody ItemDto itemDto) {
        try {
            Item item = itemService.updateItem(id, itemDto);
            ItemDto createdItemDTO = mapItemToItemDTO(item);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdItemDTO);
        } catch (ItemNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<ItemDto>> searchItem(@RequestParam(required = false) String itemNumber,
                                                    @RequestParam(required = false) String itemName) {
        List<Item> items = itemService.searchItems(itemNumber, itemName);
        List<ItemDto> itemDtos = items.stream().map(itemDto -> new ItemDto()).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(itemDtos);

    }

    private Item mapItemDTOToItem(ItemDto itemDTO) {
        Item item = new Item();
        item.setItemNumber(itemDTO.getItemNumber());
        item.setItemName(itemDTO.getItemName());
        item.setItemType(ItemType.valueOf(itemDTO.getItemType()));
        item.setStores(storeRepository.findAllById(itemDTO.getStoreId()));
        item.setItemPrice(itemDTO.getItemPrice());
        item.setItemStartDate(itemDTO.getItemStartDate());
        item.setItemEndDate(itemDTO.getItemEndDate());
        item.setSupplierName(itemDTO.getSupplierName());
        return item;
    }

    private ItemDto mapItemToItemDTO(Item item) {
        ItemDto itemDTO = new ItemDto();
        itemDTO.setItemNumber(item.getItemNumber());
        itemDTO.setItemName(item.getItemName());
        itemDTO.setItemType(String.valueOf(item.getItemType()));
        itemDTO.setStoreType(String.valueOf(item.getStores()));
        itemDTO.setItemPrice(item.getItemPrice());
        itemDTO.setItemStartDate(item.getItemStartDate());
        itemDTO.setItemEndDate(item.getItemEndDate());
        itemDTO.setSupplierName(item.getSupplierName());

        Set<Long> storeIds = new HashSet<>();
        for (Store store : item.getStores()) {
            storeIds.add(store.getId());
        }
        itemDTO.setStoreId(new ArrayList<>(storeIds));

        return itemDTO;
    }

    @DeleteMapping("/delete/{itemId}")
    ResponseEntity<Void> deleteItem(@PathVariable("itemId") Long id) {
        try {
            itemService.deleteItem(id);
            return ResponseEntity.ok().build();
        } catch (ItemNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/hello")
    public String getData() {
        return "hello ";
    }

}









