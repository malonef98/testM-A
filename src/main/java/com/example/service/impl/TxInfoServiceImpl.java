package com.example.service.impl;

import com.example.dao.BlockRepository;
import com.example.dao.TxRepository;
import com.example.pojo.Tx;
import com.example.service.TxInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class TxInfoServiceImpl implements TxInfoService {

    @Autowired
    private TxRepository txRepository;

    @Override
    public Tx selectByHash(String hash) {
        return null;
    }
}
