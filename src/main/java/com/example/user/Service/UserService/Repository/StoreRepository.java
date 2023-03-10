package com.example.user.Service.UserService.Repository;

import com.example.user.Service.UserService.Entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StoreRepository extends JpaRepository<Store,Long> {

    Optional<Store> findByStoreNo(String storeNo);
}
