package com.example.test.service.impl;

import com.example.test.dao.BlockRepository;
import com.example.test.model.BlockHead;
import com.example.test.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;


/**
 * Created by Fant.J.
 */
@Service
public class BlockServiceImpl implements BlockService {

    @Autowired
    private BlockRepository userRepository;

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    /**
     * 增加
     *
     * @param blockHead
     */
    @Override
    public void insert(BlockHead blockHead) {
        userRepository.save(blockHead);
    }

    /**
     * 更新
     *
     * @param blockHead
     */
    @Override
    public int update(BlockHead blockHead) {
        userRepository.save(blockHead);
        return 1;
    }

    /**
     * 查询单个
     *
     * @param id
     */
    @Override
    public BlockHead selectById(int id) {
        Optional<BlockHead> optional = userRepository.findById(id);
        BlockHead blockHead = optional.get();
        return blockHead;
    }

    /**
     * 查询全部列表,并做分页
     *  @param pageNum 开始页数
     * @param pageSize 每页显示的数据条数
     */
    @Override
    public Iterator<BlockHead> selectAll(int pageNum, int pageSize) {
        //将参数传给这个方法就可以实现物理分页了，非常简单。
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(pageNum, pageSize, sort);
        Page<BlockHead> blockHeads = userRepository.findAll(pageable);
        Iterator<BlockHead> userIterator =  blockHeads.iterator();
        return  userIterator;
    }
}
