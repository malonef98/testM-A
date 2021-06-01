package com.example.domain.entity;
import lombok.Data;

import javax.persistence.*;

/**
 * tx
 * @author 
 */
@Data
@Entity
public class TxInfo {
    public TxInfo(){
    }
    @Id
    @GeneratedValue()
    private Integer id;

    private String hash;
    
    private Integer blockHeight;

    //时间戳
    private String time;

    //交易费
    private String fee;

    //gas单价
    private String gasPrice;

    //gas使用量
    private String gasUsed;

    //发送地址
    private String fromAddress;

    //接收地址
    private String toAddress;

    //额度
    private String value;

    //状态
    private String state;

    //交易类型
    private String dataType;

  //  private static final long serialVersionUID = 1L;
}