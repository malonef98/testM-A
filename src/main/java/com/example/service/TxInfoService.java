package com.example.service;

import com.example.pojo.BlockHead;
import com.example.pojo.Tx;

public interface TxInfoService {
        /** 查询单个*/
        public Tx selectByHash(String hash);
}
