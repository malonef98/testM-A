package com.example.service;

import com.example.untils.JDBCUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.pojo.BlockHead;
import com.example.pojo.Tx;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Controller
public class Restful {
    /** 得到每个区块内数据  **/
    public static JSONObject getBlockData(Integer id) throws UnirestException {
        HttpResponse response =
                (HttpResponse) Unirest.get("https://info.chaindigg.com/api/api/block?coinType=eth&id="+ id +"&hash=&pageSize=100&pageNumber=0&channelId=&normal=normal").asString();
        JSONObject jso = JSON.parseObject(response.getBody().toString());
        JSONObject data = jso.getJSONObject("data");
        return data;
    }


    /** 网页爬取页数  **/
    public int getLastPage(Integer id) throws UnirestException {
        JSONObject data = getBlockData(id);
        int txconst = data.getInteger("txCount");

        int lastPageNumber = txconst / 100 ;
        return lastPageNumber;
    }


    /** 得到每个区块内所包含所有交易的hash  **/
    public List<String> getTxHash(Integer id) throws UnirestException {
        int lastPageNumber =  getLastPage(id);
        List<String> hash = new ArrayList<String>();
        List<Tx> tx = new ArrayList<Tx>();
        Tx com = new Tx();
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


    /** 根据交易hash值获取每个交易的详细数据  **/
    public Tx getTX(String txhash) throws UnirestException {
                HttpResponse response =
                        Unirest.get("https://info.chaindigg.com/api/api/txn?coinType=eth&hash=" + txhash + "&channelId="
                        ).asString();
                Tx com = new Tx();
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

                return com;
            }


    /** json转化list **/
    public JSONArray getTxList(List<String> txhash)
    {

        JSONArray jsonArray = new JSONArray();
        for(String  hash : txhash){
            JSONObject jo = new JSONObject();
            jo.put("txhash", hash);
            jsonArray.add(jo);
        }
        return jsonArray;
    }


    /** Restful 风格接口 **/
    @RequestMapping(value = "/add/{id}",method = RequestMethod.POST)
    public ErrorMessage addId (@PathVariable("id") int id , HttpServletRequest request, HttpServletResponse response){
        try {
            insertDB(id);
        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new ErrorMessage(0,null);
    }

    @CrossOrigin
    @RequestMapping(value = "/block/{id}",method = RequestMethod.GET)
    @ResponseBody
    public BlockHead getId (@PathVariable("id") int id ){
        BlockHead m = new BlockHead();
        List<String> total = new ArrayList<>();
        try {
            m = selectDB(id);
            System.out.println(m.getId());
            System.out.println(m.getTotalDifficulty());
            System.out.println(m.getNextBlockHash());
            System.out.println(m.getMiner());
            System.out.println(m.getExtraData());

            total = selectTotalTx(m.getHash());
           // m.setTotaltx(total);

        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return m;
    }

    @CrossOrigin
    @RequestMapping(value = "/hash/{hash}",method = RequestMethod.GET)
    @ResponseBody
    public Tx gettxhash (@PathVariable("hash") String hash ){
        Tx m = new Tx();

        try {
            m = selectTx(hash);

        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return m;
    }


    @RequestMapping(value = "/delete/{id}",method = RequestMethod.DELETE)
    public  void deleteId (@PathVariable("id") int id ){

    }

    //查找特点id区块信息
    public static BlockHead selectDB(int i) throws UnirestException, SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        JSONObject data = getBlockData(i);
        BlockHead bh = JSON.parseObject(data.toString(), BlockHead.class);
        BlockHead one = new BlockHead();
        try {
            con = JDBCUtils.getConnection();
            String sql = "select * from block_info where id = ?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, i);



            //执行sql
            ResultSet result = stmt.executeQuery();// 返回值代表收到影响的行数

            ResultSetMetaData metaData = result.getMetaData();
            System.out.println(metaData.getColumnCount());
            while (result.next()) {
                one.setTotalDifficulty(result.getString("totalDifficulty"));
                one.setNextBlockHash(result.getString("nextBlockHash"));
                one.setExtraData(result.getString("extraData"));
                one.setFee(result.getString("fee"));
                one.setMiner(result.getString("miner"));
                one.setGasUsed(result.getInt("gasUsed"));
                one.setId(result.getInt("id"));
                one.setHeight(result.getInt("height"));
                one.setHash(result.getString("hash"));
                one.setTxCount(result.getString("txCount"));
                one.setTotalValue(result.getString("totalValue"));
                one.setTime(result.getString("time_stamp"));
                one.setSize(result.getInt("block_size"));
                one.setReward(result.getString("reward"));
                one.setDifficulty(result.getString("difficulty"));
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, stmt, con);
        }
          return one;
    }

    //查找指定hash交易信息
    public static Tx selectTx(String i) throws UnirestException, SQLException {
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Tx one = new Tx();

        try {
            con = JDBCUtils.getConnection();
            String sql = "select * from tx_info where txhash = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, i);



            //执行sql
            ResultSet result = stmt.executeQuery();// 返回值代表收到影响的行数

            ResultSetMetaData metaData = result.getMetaData();
            System.out.println(metaData.getColumnCount());
            while (result.next()) {
                one.setHash(result.getString("txhash"));
                one.setBlockHeight(result.getInt("blockHeight"));
                one.setFromAddress(result.getString("fromAddressHash"));
                one.setToAddress(result.getString("toAddressHash"));
                one.setGasUsed(result.getString("gasUsed"));
                one.setGasPrice(result.getString("gasPrice"));
                one.setFee(result.getString("fee"));
                one.setTime(result.getString("time_stamp"));
                one.setDataType(result.getString("dataType"));
                one.setDesc(result.getString("b_desc"));
                one.setValue(result.getString("outputTotal"));
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, stmt, con);
        }
        return one;
    }

    //数据库存入信息
    public static void insertDB ( int i) throws UnirestException, SQLException {
            Restful a = new Restful();

            Connection con = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;

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

            List<String> txhash = a.getTxHash(bh.getId());
            List<Tx> tx_info = new ArrayList<Tx>();
            for (String hash : txhash) {
                tx_info.add(a.getTX(hash));
            }

            insertService insert = new insertService();
            insert.insertTx(tx_info);
            insert.insertTxlist(txhash, bh.getHash());

            try {
                con = JDBCUtils.getConnection();
                String sql = "insert into block_info(totalDifficulty,nextBlockHash,extraData,fee,miner,gasUsed," +
                        "id,height,hash,txCount) values(?,?,?,?,?,?,?,?,?,?)";
                stmt = con.prepareStatement(sql);
                stmt.setString(1, bh.getTotalDifficulty());
                stmt.setString(2, bh.getNextBlockHash());
                stmt.setString(3, bh.getExtraData());
                stmt.setString(4, bh.getFee());
                stmt.setString(5, bh.getMiner());
                stmt.setInt(6, bh.getGasUsed());
                stmt.setInt(7, bh.getId());
                stmt.setInt(8, bh.getHeight());
                stmt.setString(9, bh.getHash());
                stmt.setString(10, bh.getTxCount());

                //执行sql
                int result = stmt.executeUpdate();// 返回值代表收到影响的行数
                System.out.println("插入成功" + bh.getId());
                // System.out.println("#######"+bh.getTxHash());
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                JDBCUtils.close(rs, stmt, con);
            }

        }

    //查找区块对应所有交易
    public static List<String> selectTotalTx (String block_hash){
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<String> tx = new ArrayList<>();

        try {
            con = JDBCUtils.getConnection();
            String sql = "SELECT  tx_hash from block_info LEFT  JOIN tx_list ON block_info.`hash` = tx_list.block_hash WHERE block_hash = ?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, block_hash);

            //执行sql
            ResultSet result = stmt.executeQuery();// 返回值代表收到影响的行数
            ResultSetMetaData metaData = result.getMetaData();
            while (result.next()) {
                  tx.add(result.getString("tx_hash") + "\n");
            }
            System.out.println(metaData.getColumnCount());
           } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return tx;
    }
}
