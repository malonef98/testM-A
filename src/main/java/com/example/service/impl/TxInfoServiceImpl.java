package com.example.service.impl;

import com.example.dao.TxRepository;
import com.example.domain.entity.TxInfoEntity;
import com.example.service.TxInfoService;
import com.example.untils.FindInfo;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TxInfoServiceImpl implements TxInfoService {

    @Autowired
    private TxRepository txRepository;

    @Override
    public TxInfoEntity selectByHash(String hash) {
        return txRepository.findByHash(hash);
    }

    @Override
    public TxInfoEntity saveByHash(String txhash) {
        FindInfo findInfo = new FindInfo();
        TxInfoEntity txInfoEntity = new TxInfoEntity();
        if (txRepository.findByHash(txhash) == null){
            try {
                txInfoEntity = findInfo.findTxByHash(txhash);
            } catch (UnirestException e) {
                log.error("按照交易哈希查找交易错误",e);
            }
            txRepository.saveAndFlush(txInfoEntity);
        }else {
            return txRepository.findByHash(txhash);
        }
        return txInfoEntity;
    }

}
