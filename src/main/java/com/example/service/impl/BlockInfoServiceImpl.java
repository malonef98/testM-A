package com.example.service.impl;

import com.example.dao.BlockRepository;
import com.example.domain.entity.BlockHead;
import com.example.domain.entity.TxInfo;
import com.example.service.*;
import com.example.untils.FindInfo;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * Created by Fant.J.
 */
@Service
@Slf4j
public class BlockInfoServiceImpl implements BlockInfoService {

    @Autowired
    private BlockRepository blockRepository;

    @Override
    public BlockHead selectById(int id) {
        Optional<BlockHead> optional = blockRepository.findById(id);
        BlockHead blockHead = optional.get();
        return blockHead;
    }

    @Override
    public BlockHead selectByHeight(int height) {
        return blockRepository.findByHeight(height);
    }

    @Override
    public BlockHead selectByFee(String fee) {
        BlockHead blockHead = blockRepository.findByFee(fee);
        return blockHead;
    }

    @Override
    public void save() {
        FindInfo findInfo = new FindInfo();
        BlockHead bh = new BlockHead();
        TxInfo txInfo = new TxInfo();
        List<String> txCollection = new ArrayList<String>();
        for (int i = 4000000 ; i < 4001000 ; i ++){
            try {
                bh = findInfo.findBlockByHeight(i);
                Set<String> Txcollection = new HashSet<>();
                txCollection = findInfo.getTxHash(bh.getHeight());
                for (String one : txCollection){
                    Txcollection.add(one);
                }
                bh.setTXcollection(Txcollection);
            } catch (UnirestException e) {
                log.error("按照区块高度查询区块信息错误",e);                 //打印日志和错误
            }
            blockRepository.saveAndFlush(bh);
        }
    }
}
