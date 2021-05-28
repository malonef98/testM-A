package com.example.service.impl;

import com.example.dao.BlockRepository;
import com.example.pojo.BlockHead;
import com.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * Created by Fant.J.
 */
@Service
public class BlockInfoServiceImpl implements BlockInfoService {

    @Autowired
    private BlockRepository blockRepository;


    /**
     * 查询单个
     *
     * @param id
     */
    @Override
    public BlockHead selectById(int id) {
        Optional<BlockHead> optional = blockRepository.findById(id);
        BlockHead blockHead = optional.get();
        return blockHead;
    }
}
