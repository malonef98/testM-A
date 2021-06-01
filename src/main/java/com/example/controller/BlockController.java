package com.example.controller;

import com.example.domain.entity.BlockHead;
import com.example.service.BlockInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/block_info")
public class  BlockController {

    @Autowired
    private BlockInfoService blockInfoService;

    /**
     * 按照数据库ID查询区块数据，返回BlockHead结构体给前端
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/id/{id}")
    @ResponseBody
    public BlockHead select(@PathVariable("id")int id){
        return blockInfoService.selectById(id);
    }

    /**
     * 按照数据库height查询区块数据，返回BlockHead结构体给前端
     * @param height
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/height/{height}")
    @ResponseBody
    public BlockHead selectByheight(@PathVariable("height")int height){
        return blockInfoService.selectByHeight(height);
    }

    /**
     * 按照数据库fee查询区块数据，返回BlockHead结构体给前端
     * @param fee
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/fee/{fee}")
    @ResponseBody
    public BlockHead select(@PathVariable("fee")String fee){
        return blockInfoService.selectByFee(fee);
    }

    /**
     * 从ChaindiggInfo查询数据，并且存储区块数据带数据库
     */
    @RequestMapping(method = RequestMethod.GET,value = "/save")
    @ResponseBody
    public void select(){
        blockInfoService.save();
    }

}
