package com.example.user.Service.UserService.Impl;
import com.example.user.Service.UserService.Dto.ItemDto;
import com.example.user.Service.UserService.Entity.Item;
import com.example.user.Service.UserService.Entity.Store;
import com.example.user.Service.UserService.Enums.ItemType;
import com.example.user.Service.UserService.Exception.ResourceNotFoundException;
import com.example.user.Service.UserService.Repository.ItemRepository;
import com.example.user.Service.UserService.Repository.StoreRepository;
import com.example.user.Service.UserService.Service.ItemService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
@Slf4j
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    private final StoreRepository storeRepository;

    public ItemServiceImpl(ItemRepository itemRepository, StoreRepository storeRepository) {
        this.itemRepository = itemRepository;
        this.storeRepository = storeRepository;
    }


    @Override
    public Item createItem(ItemDto itemDto) {
        Item item = new Item();
        item.setItemNumber(itemDto.getItemNumber());
        item.setItemName(itemDto.getItemName());
        item.setItemType(ItemType.valueOf(itemDto.getItemType()));
        item.setStores(new ArrayList<>());
        item.setItemPrice(itemDto.getItemPrice());
        item.setItemStartDate(itemDto.getItemStartDate());
        item.setItemEndDate(itemDto.getItemEndDate());
        item.setSupplierName(itemDto.getSupplierName());

        for (Long storeId : itemDto.getStoreId()) {
            Store store = storeRepository.findById(storeId).orElseThrow(() -> new EntityNotFoundException("Store not found"));
            store.getItem().add(item);
            item.getStores().add(store);
        }
        Item savedItem = itemRepository.save(item);
        return savedItem;


    }

    @Override
    public Item updateItem(Long id,ItemDto itemDto) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            item.setItemNumber(itemDto.getItemNumber());
            item.setItemName(itemDto.getItemName());
            item.setItemType(ItemType.valueOf(itemDto.getItemType()));
            item.setItemPrice(itemDto.getItemPrice());
            item.setItemStartDate(itemDto.getItemStartDate());
            item.setItemEndDate(itemDto.getItemEndDate());
            item.setSupplierName(itemDto.getSupplierName());

            // Remove existing stores that are not in the updated list of storeIds
            Set<Long> newStoreIds = new HashSet<>(itemDto.getStoreId());
            for (Iterator<Store> iterator = item.getStores().iterator(); iterator.hasNext();) {
                Store store = iterator.next();
                if (!newStoreIds.contains(store.getId())) {
                    store.getItem().remove(item);
                    iterator.remove();
                }
            }

            // Add new stores that are not already in the existing stores
            for (Long storeId : itemDto.getStoreId()) {
                Optional<Store> optionalStore = storeRepository.findById(storeId);
                optionalStore.ifPresent(store -> {
                    if (!item.getStores().contains(store)) {
                        item.getStores().add(store);
                        store.getItem().add(item);
                    }
                });
            }

            Item savedItem = itemRepository.save(item);
            return savedItem;
        } else {
            throw new ResourceNotFoundException("Item not found with id "+id);

        }
    }

    @Override
    public void deleteItem(Long id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isPresent()) {
            Item item = optionalItem.get();
            for (Store store : item.getStores()) {
                store.getItem().remove(item);
            }
            itemRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Item not found with id " + id);
        }


    }

    @Override
    public List<Item> searchItems(String itemNumber, String itemName) {
        if (itemNumber != null && !itemNumber.isEmpty()) {
            return itemRepository.findByItemNumberContainingIgnoreCase(itemNumber);
        } else if (itemName != null && !itemName.isEmpty()) {
            return itemRepository.findByItemNameContainingIgnoreCase(itemName);
        } else {
            return itemRepository.findAll();
        }
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
        itemDTO.setStoreId((List<Long>) storeIds);

        return itemDTO;
    }
    }





