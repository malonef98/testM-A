package com.example.service.impl;

import com.example.dao.TxRepository;
import com.example.domain.entity.TxInfo;
import com.example.service.TxInfoService;
import com.example.untils.FindInfo;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TxInfoServiceImpl implements TxInfoService {

    @Autowired
    private TxRepository txRepository;

    @Override
    public TxInfo selectByHash(String hash) {
        return txRepository.findByHash(hash);
    }

    @Override
    public TxInfo saveByHash(String txhash) {
        FindInfo findInfo = new FindInfo();
        TxInfo txInfo = new TxInfo();
        if (txRepository.findByHash(txhash) == null){
            try {
                txInfo = findInfo.findTxByHash(txhash);
            } catch (UnirestException e) {
                e.printStackTrace();
            }
            txRepository.saveAndFlush(txInfo);
        }else {
            return txRepository.findByHash(txhash);
        }

        return txInfo;
    }

}
