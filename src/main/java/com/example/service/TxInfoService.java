package com.example.service;

import com.example.domain.entity.TxInfo;

public interface TxInfoService {


    /**
     * 按照交易hash值查询交易详细信息
     * @param hash
     * @return
     */
    TxInfo selectByHash(String hash);

    /**
     * 存储交易数据都数据库
     * @param txhash
     * @return
     */
    TxInfo saveByHash(String txhash);
}
