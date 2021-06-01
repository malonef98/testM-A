package com.example.controller;

import com.example.domain.entity.BlockHead;
import com.example.service.BlockInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Fant.J.
 */
@RestController
@RequestMapping("/block_info")
public class BlockController {

    @Autowired
    private BlockInfoService blockInfoService;

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/id/{id}")
    @ResponseBody
    public BlockHead select(@PathVariable("id")int id){
        return blockInfoService.selectById(id);
    }

    /**
     *
     * @param height
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/height/{height}")
    @ResponseBody
    public BlockHead selectByheight(@PathVariable("height")int height){
        return blockInfoService.selectByHeight(height);
    }

    /**
     *
     * @param fee
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/fee/{fee}")
    @ResponseBody
    public BlockHead select(@PathVariable("fee")String fee){
        return blockInfoService.selectByFee(fee);
    }

    /**
     *
     */
    @RequestMapping(method = RequestMethod.GET,value = "/save")
    @ResponseBody
    public void select(){
        blockInfoService.save();
    }

}
