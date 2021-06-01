package com.example.dao;

import com.example.domain.entity.TxInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TxRepository extends JpaRepository<TxInfoEntity, Integer> {
       TxInfoEntity findByHash(String hash);
}
