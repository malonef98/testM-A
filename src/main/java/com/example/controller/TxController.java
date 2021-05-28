package com.example.controller;

import com.example.pojo.Tx;
import com.example.service.TxInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Fant.J.
 */
@RestController
@RequestMapping("/user")
public class TxController {

    @Autowired
    private TxInfoService txInfoService;

    @RequestMapping(method = RequestMethod.GET,value = "/{hash}/select")
    public Tx select(@PathVariable("hash")String hash){
        return txInfoService.selectByHash(hash);
    }

}
