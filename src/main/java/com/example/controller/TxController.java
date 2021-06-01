package com.example.controller;

import com.example.domain.entity.TxInfo;
import com.example.service.TxInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tx_info")
public class TxController {

    @Autowired
    private TxInfoService txInfoService;

    /**
     * 按照交易哈希txhash,查询交易详细数据
     * @param txhash
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/txhash/{txhash}")
    public TxInfo selectByTxhash(@PathVariable("txhash")String txhash){
        return txInfoService.selectByHash(txhash);
    }

    /**
     * 按照交易哈希txhash,查询交易交易数据并且存储到数据库内
     * @param hash
     * @return
     */
    @RequestMapping(method = RequestMethod.GET,value = "/saveTx/{hash}")
    @ResponseBody
    public TxInfo saveByTxhash(@PathVariable("hash")String hash){

        return txInfoService.saveByHash(hash);
    }

}
