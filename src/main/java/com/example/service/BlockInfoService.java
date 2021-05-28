package com.example.service;


import com.example.pojo.BlockHead;
import java.util.Iterator;

/**
 * Created by Fant.J.
 */
public interface BlockInfoService {
    /** 查询单个*/
    public BlockHead selectById(int id);
}


