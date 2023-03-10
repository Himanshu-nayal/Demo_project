package com.example.user.Service.UserService.Service;

import com.example.user.Service.UserService.Dto.StoreDto;
import com.example.user.Service.UserService.Entity.Store;

public interface StoreService {


    Store createStore(StoreDto storeDto);

    Store updateStore(Long id, StoreDto storeDto);

    Store deleteStore(Long id);
}