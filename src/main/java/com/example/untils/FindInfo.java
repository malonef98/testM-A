package com.example.untils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.domain.entity.BlockHead;
import com.example.domain.entity.TxInfo;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.jetbrains.annotations.TestOnly;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindInfo {

    /**
     *  从ChaindiggInfo获取区块数据
     * @param id
     * @return
     * @throws UnirestException
     */
    public static JSONObject getBlockData(Integer id) throws UnirestException {
        HttpResponse response =
                (HttpResponse) Unirest.get("https://info.chaindigg.com/api/api/block?coinType=eth&id="+ id +"&hash=&pageSize=100&pageNumber=0&channelId=&normal=normal").asString();
        JSONObject jso = JSON.parseObject(response.getBody().toString());
        JSONObject data = jso.getJSONObject("data");
        return data;
    }


    public int getLastPage(Integer id) throws UnirestException {
        JSONObject data = getBlockData(id);
        int txconst = data.getInteger("txCount");

        int lastPageNumber = txconst / 100 ;
        return lastPageNumber;
    }


    public  BlockHead findBlockByHeight(int i) throws UnirestException {

        JSONObject data = getBlockData(i);
        BlockHead bh = JSON.parseObject(data.toString(), BlockHead.class);

        bh.setTotalDifficulty(data.getString("totalDifficulty"));
        bh.setNextBlockHash(data.getString("nextBlockHash"));
        bh.setExtraData(data.getString("extraData"));
        bh.setFee(data.getString("fee"));
        bh.setMiner(data.getString("miner"));
        bh.setGasUsed(data.getInteger("gasUsed"));
        bh.setId(data.getInteger("id"));
        bh.setHeight(data.getInteger("height"));
        bh.setHash(data.getString("hash"));
        bh.setTxCount(data.getString("txCount"));
        bh.setTotalValue(data.getString("totalValue"));
        bh.setTime(data.getString("time"));
        bh.setSize(data.getInteger("size"));
        bh.setReward(data.getString("totalReward"));
        bh.setDifficulty(data.getString("difficulty"));

            return bh;
    }


    public TxInfo findTxByHash(String txhash) throws UnirestException {
        HttpResponse response = null;
            response = Unirest.get("https://info.chaindigg.com/api/api/txn?coinType=eth&hash=" + txhash + "&channelId="
            ).asString();

        TxInfo com = new TxInfo();
        JSONObject jso = JSON.parseObject(response.getBody().toString());
        JSONObject data = jso.getJSONObject("data");

        //高度
        com.setBlockHeight(data.getInteger("blockHeight"));
        //交易hash
        com.setHash(data.get("hash").toString());
        //发送地址
        com.setFromAddress(data.get("fromAddressHash").toString());
        //接收地址
        com.setToAddress(data.get("toAddressHash").toString());
        //gas使用量
        com.setGasUsed(data.get("gasUsed").toString());
        //gas单价
        com.setGasPrice(data.get("gasPrice").toString());
        //交易费
        com.setFee(data.get("fee").toString());
        //时间
        com.setTime(data.get("time").toString());
        //数据类型
        com.setDataType(data.get("dataType").toString());
        //交易状态
        com.setState(jso.getString("desc"));
        //额度
        com.setValue(data.get("outputTotal").toString());

        return com;
    }


    public List<String> getTxHash(Integer id) throws UnirestException {
        int lastPageNumber =  getLastPage(id);
        List<String> hash = new ArrayList<String>();
        List<TxInfo> txInfos = new ArrayList<TxInfo>();
        TxInfo com = new TxInfo();
        if (lastPageNumber == 0) {
            HttpResponse response =
                    Unirest.get("https://info.chaindigg.com/api/api/block?coinType=eth&id="+id+"+&hash=&pageSize=100&pageNumber=" + 0 + "&channelId=&normal=normal").asString();
            JSONObject jso = JSON.parseObject(response.getBody().toString());
            JSONObject data = jso.getJSONObject("data");
            JSONArray txnInfoVoList = data.getJSONArray("txnInfoVoList");
            for (int a = 0; a < txnInfoVoList.size(); a++) {
                JSONObject index = txnInfoVoList.getJSONObject(a);
                hash.add(index.get("txHash").toString());
            }
        }else {
            for (int i = 0; i <= lastPageNumber; i++) {
                HttpResponse response =
                        Unirest.get("https://info.chaindigg.com/api/api/block?coinType=eth&id="+id+"+&hash=&pageSize=100&pageNumber=" + i + "&channelId=&normal=normal").asString();
                JSONObject jso = JSON.parseObject(response.getBody().toString());
                JSONObject data = jso.getJSONObject("data");
                String status = jso.getString("desc");
                JSONArray txnInfoVoList = data.getJSONArray("txnInfoVoList");
                for (int a = 0; a < txnInfoVoList.size(); a++) {
                    JSONObject index = txnInfoVoList.getJSONObject(a);
                    hash.add(index.get("txHash").toString());
                }
            }
        }
        return hash;
    }


}
