package com.example.service;


import com.example.domain.entity.BlockHeadEntity;

/**
 * Created by Fant.J.
 */
public interface BlockInfoService {
    /**
     * 按照ID查询区块数据，返回区块结构体
     * @param id
     * @return
     */
    BlockHeadEntity selectById(int id);

    /**
     * 按照height区块高度查询区块数据，返回区块结构体
     * @param height
     * @return
     */
    BlockHeadEntity selectByHeight(int height);

    /**
     * 按照fee费用查询区块数据，返回区块结构体
     * @param fee
     * @return
     */
    BlockHeadEntity selectByFee(String fee);

    /**
     * 存储区块信息到数据库
     * @return
     */
    void save();
}


