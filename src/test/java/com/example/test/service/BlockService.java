package com.example.test.service;


import com.example.test.model.BlockHead;

import java.util.Iterator;

/**
 * Created by Fant.J.
 */
public interface BlockService {
    /** 删除 */
    public void delete(int id);
    /** 增加*/
    public void insert(BlockHead blockHead);
    /** 更新*/
    public int update(BlockHead blockHead);
    /** 查询单个*/
    public BlockHead selectById(int id);
    /** 查询全部列表*/
    public Iterator<BlockHead> selectAll(int pageNum, int pageSize);
}


