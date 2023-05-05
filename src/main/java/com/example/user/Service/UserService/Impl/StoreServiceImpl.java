package com.example.user.Service.UserService.Impl;

import com.example.user.Service.UserService.Dto.StoreDto;
import com.example.user.Service.UserService.Entity.Store;
import com.example.user.Service.UserService.Exception.StoreAlreadyExistsException;
import com.example.user.Service.UserService.Exception.StoreNotFoundException;
import com.example.user.Service.UserService.Repository.StoreRepository;
import com.example.user.Service.UserService.Service.StoreService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@Slf4j
public class StoreServiceImpl implements StoreService {
    @Autowired
    StoreRepository storeRepository;

    @Override
    public Store createStore(StoreDto storeDto) throws StoreAlreadyExistsException {
        Optional<Store> optionalStore = storeRepository.findByStoreNo(storeDto.getStoreNo());
        log.info("operational store value :{}", optionalStore);
        if(optionalStore.isPresent()){
            throw new StoreAlreadyExistsException();
        }
        Store store=new Store();
        store.setStoreNo(storeDto.getStoreNo());
        store.setStoreName(storeDto.getStoreName());
        store.setPinCode(store.getPinCode());
        store.setStoreLocation(storeDto.getStoreLocation());
        store.setStorePattern(storeDto.getStorePattern());
        store.setOpeningTime(storeDto.getOpeningTime());
        store.setClosingTime(storeDto.getClosingTime());
        return storeRepository.save(store);

    }

    @Override
    public Store updateStore(Long id, StoreDto storeDto) throws StoreNotFoundException {
        Optional<Store> optionalStore = storeRepository.findById(id);
        if(optionalStore.isEmpty()){
            throw new StoreNotFoundException();
        }
        Store store = optionalStore.get();
        store.setStoreName(storeDto.getStoreName());
        store.setStoreLocation(storeDto.getStoreLocation());
        store.setPinCode(storeDto.getPinCode());
        store.setOpeningTime(storeDto.getOpeningTime());
        store.setClosingTime(storeDto.getClosingTime());
        return storeRepository.save(store);

    }

    @Override
    public Store deleteStore(Long id) {
        Store existingStore = storeRepository.findById(id).orElseThrow(StoreNotFoundException::new);
       storeRepository.delete(existingStore);
       return null;
    }
}
