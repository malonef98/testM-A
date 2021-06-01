package com.example.service.impl;

import com.example.dao.BlockRepository;
import com.example.domain.entity.BlockHeadEntity;
import com.example.domain.entity.TxInfoEntity;
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
    public BlockHeadEntity selectById(int id) {
        Optional<BlockHeadEntity> optional = blockRepository.findById(id);
        BlockHeadEntity blockHeadEntity = optional.get();
        return blockHeadEntity;
    }

    @Override
    public BlockHeadEntity selectByHeight(int height) {
        return blockRepository.findByHeight(height);
    }

    @Override
    public BlockHeadEntity selectByFee(String fee) {
        BlockHeadEntity blockHeadEntity = blockRepository.findByFee(fee);
        return blockHeadEntity;
    }

    @Override
    public void save() {
        FindInfo findInfo = new FindInfo();
        BlockHeadEntity bh = new BlockHeadEntity();
        TxInfoEntity txInfoEntity = new TxInfoEntity();
        List<String> txCollection = new ArrayList<String>();
        for (int i = 4000000 ; i < 4001000 ; i ++){
            try {
                bh = findInfo.findBlockByHeight(i);
                Set<String> collection = new HashSet<>();
                txCollection = findInfo.getTxHash(bh.getHeight());
                for (String one : txCollection){
                    collection.add(one);
                }
                bh.setTxCollection(collection);
            } catch (UnirestException e) {
                log.error("按照区块高度查询区块信息错误",e);                 //打印日志和错误
            }
            blockRepository.saveAndFlush(bh);
        }
    }
}
