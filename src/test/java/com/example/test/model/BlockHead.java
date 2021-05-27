package com.example.test.model;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "block_info")
public class BlockHead {
        public BlockHead(){
        }
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)

    //总难度
    private String totalDifficulty;
    @Column(nullable = false)

    //下一区块hash
    private String nextBlockHash;
    @Column(nullable = false)

    //额外数据
    private String extraData;
    @Column(nullable = false)

    //总交易费
    private String fee;
    @Column(nullable = false)

    //矿工
    private String miner;
    @Column(nullable = false)

    //gas使用量
    private Integer gasUsed;
    @Column(nullable = false)

    private Integer id;
    @Column(nullable = false)

    //区块高度
    private Integer height;
    @Column(nullable = false)

    //块hash值
    private String hash;
    @Column(nullable = false)

    //交易总数
    private String txCount;
    @Column(nullable = false)

    //总交易额
    private String totalValue;
    @Column(nullable = false)

    //时间戳
    private String time;
    @Column(nullable = false)

    //区块大小
    private Integer size;
    @Column(nullable = false)

    //挖矿奖励
    private String reward;
    @Column(nullable = false)

    //难度
    private String difficulty;


//    //交易列表
//    private List<String> Totaltx;
//    @Column(nullable = false)
}
