package com.example.dao;

import com.example.domain.entity.TxInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TxRepository extends JpaRepository<TxInfo, Integer> {
       TxInfo findByHash(String hash);
}
