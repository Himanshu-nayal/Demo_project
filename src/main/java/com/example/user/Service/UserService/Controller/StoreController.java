package com.example.user.Service.UserService.Controller;

import com.example.user.Service.UserService.Dto.StoreDto;
import com.example.user.Service.UserService.Entity.Store;
import com.example.user.Service.UserService.Exception.StoreAlreadyExistsException;
import com.example.user.Service.UserService.Exception.StoreNotFoundException;
import com.example.user.Service.UserService.Service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("api/user")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @PostMapping("/create")
    ResponseEntity<Store> createStore(@RequestBody StoreDto storeDto) {
        try {
            Store store = storeService.createStore(storeDto);
            return ResponseEntity.created(URI.create("api/user/" + store.getId())).body(store);
        } catch (StoreAlreadyExistsException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/{storeId}")
    ResponseEntity<Store> updateStore(@PathVariable("storeId") Long id, @RequestBody StoreDto storeDto) {
        try {
            Store store = storeService.updateStore(id, storeDto);
            return ResponseEntity.status(HttpStatus.OK).body(store);

        } catch (StoreNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @DeleteMapping("/delete/{storeId}")
    public ResponseEntity<Void> deleteStore(@PathVariable("storeId") Long id){
        try{
            Store store=storeService.deleteStore(id);
            return ResponseEntity.notFound().build();
        }

        catch (StoreNotFoundException e){
            return   ResponseEntity.notFound().build();
        }
    }
}
