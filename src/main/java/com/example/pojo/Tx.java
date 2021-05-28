package com.example.pojo;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * tx
 * @author 
 */
@Data
@Entity
@Table(name = "tx_info")
public class Tx  {
    public Tx(){
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private String hash;
    @Column(nullable = false)

    private Integer blockHeight;
    @Column(nullable = false)

    //时间戳
    private String time;
    @Column(nullable = false)

    //交易费
    private String fee;
    @Column(nullable = false)

    //gas单价
    private String gasPrice;
    @Column(nullable = false)

    //gas使用量
    private String gasUsed;
    @Column(nullable = false)

    //发送地址
    private String fromAddress;
    @Column(nullable = false)

    //接收地址
    private String toAddress;
    @Column(nullable = false)

    //额度
    private String value;
    @Column(nullable = false)

    //状态
    private String desc;
    @Column(nullable = false)

    //交易类型
    private String dataType;

  //  private static final long serialVersionUID = 1L;
}