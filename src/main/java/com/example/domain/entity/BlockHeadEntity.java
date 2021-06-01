package com.example.domain.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;


/**
 * blockhead
 * @author
 */
@Data
@Entity
public class BlockHeadEntity {
    public BlockHeadEntity(){
    }
    @Id
    @GeneratedValue()
    private Integer id;

    //块hash值
    private String hash;

    //总难度
    private String totalDifficulty;

    //下一区块hash
    private String  nextBlockHash;

    //额外数据
    private String  extraData;

    //总交易费
    private String  fee;

    //矿工
    private String  miner;

    //gas使用量
    private Integer gasUsed;

    //区块高度
    private Integer height;

    //交易总数
    private String  txCount;

    //总交易额
    private String  totalValue;

    //时间戳
    private String  time;

    //区块大小
    private Integer size;

    //挖矿奖励
    private String reward;

    //难度
    private String difficulty;

    //交易列表
    @ElementCollection
    private Set<String> txCollection;
}

