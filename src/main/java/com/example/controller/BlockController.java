package com.example.controller;

import com.example.pojo.BlockHead;
import com.example.service.BlockInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Fant.J.
 */
@RestController
@RequestMapping("/user")
public class BlockController {

    @Autowired
    private BlockInfoService blockInfoService;

    @RequestMapping(method = RequestMethod.GET,value = "/{id}/select")
    @ResponseBody
    public BlockHead select(@PathVariable("id")int id){

        return blockInfoService.selectById(id);
    }

}
