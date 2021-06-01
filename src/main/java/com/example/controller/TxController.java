package com.example.controller;

import com.example.domain.entity.TxInfo;
import com.example.service.TxInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Fant.J.
 */
@RestController
@RequestMapping("/tx_info")
public class TxController {

    @Autowired
    private TxInfoService txInfoService;

    @RequestMapping(method = RequestMethod.GET,value = "/txhash/{txhash}")
    public TxInfo selectByTxhash(@PathVariable("txhash")String hash){
        return txInfoService.selectByHash(hash);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/saveTx/{hash}")
    @ResponseBody
    public TxInfo saveByTxhash(@PathVariable("hash")String hash){

        return txInfoService.saveByHash(hash);
    }

}
