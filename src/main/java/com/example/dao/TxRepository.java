package com.example.dao;

import com.example.pojo.Tx;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TxRepository extends JpaRepository<Tx, Integer> {

}
