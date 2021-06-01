package com.example.test.before;

import com.example.domain.entity.TxInfo;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class insertService {
    /** 向tx_list表放入数据  区块：交易哈希 **/
    public void insertTxlist(List<String> txhash,String blockhash) throws SQLException {
        //注册驱动    使用驱动连接数据库
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        con = JDBCUtils.getConnection();
        //
        String sql = "insert into tx_list(block_hash,tx_hash) values(?,?)";

        try {
            for (String hash : txhash) {
                stmt = con.prepareStatement(sql);
                stmt.setString(1,blockhash);
                stmt.setString(2,hash);
                int result =stmt.executeUpdate();// 返回值代表收到影响的行数
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, stmt, con);
        }
    }


    /** 向tx_info表放入数据   每个交易具体数据**/
    public void insertTx(List<TxInfo> txlist) throws SQLException {
        TxInfo txInfo = new TxInfo();
        //注册驱动    使用驱动连接数据库
        Connection con = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        con = JDBCUtils.getConnection();

        //sql语句
        String sql = "insert into tx_info(txhash,blockHeight,fromAddressHash,toAddressHash," +
                "gasUsed,gasPrice,fee) values(?,?,?,?,?,?,?)";
        try {
            for (TxInfo t : txlist){
                stmt = con.prepareStatement(sql);
                stmt.setString(1,t.getHash());
                stmt.setInt(2,t.getBlockHeight());
                stmt.setString(3,t.getFromAddress());
                stmt.setString(4,t.getToAddress());
                stmt.setString(5,t.getGasUsed());
                stmt.setString(6,t.getGasPrice());
                stmt.setString(7,t.getFee());
                // 执行sql操作，返回值代表收到影响的行数
                stmt.executeUpdate();
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            JDBCUtils.close(rs, stmt, con);
        }
    }
}


