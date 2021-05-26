package com.example.pojo;
import lombok.Data;
import java.io.Serializable;
import java.util.List;

/**
 * blockhead
 * @author
 */
@Data
public class BlockHead implements Serializable {
    //总难度
    private String totalDifficulty;

    //下一区块hash
    private String nextBlockHash;

    //额外数据
    private String extraData;

    //总交易费
    private String fee;

    //矿工
    private String miner;

    //gas使用量
    private Integer gasUsed;


    private Integer id;

    //区块高度
    private Integer height;

    public String getTotalDifficulty() {
        return totalDifficulty;
    }

    public void setTotalDifficulty(String totalDifficulty) {
        this.totalDifficulty = totalDifficulty;
    }

    public String getNextBlockHash() {
        return nextBlockHash;
    }

    public void setNextBlockHash(String nextBlockHash) {
        this.nextBlockHash = nextBlockHash;
    }

    public String getExtraData() {
        return extraData;
    }

    public void setExtraData(String extraData) {
        this.extraData = extraData;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getMiner() {
        return miner;
    }

    public void setMiner(String miner) {
        this.miner = miner;
    }

    public Integer getGasUsed() {
        return gasUsed;
    }

    public void setGasUsed(Integer gasUsed) {
        this.gasUsed = gasUsed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

//    public Integer getConfirms() {
//        return confirms;
//    }
//
//    public void setConfirms(Integer confirms) {
//        this.confirms = confirms;
//    }

    public String getTxCount() {
        return txCount;
    }

    public void setTxCount(String txCount) {
        this.txCount = txCount;
    }

    public String getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(String totalValue) {
        this.totalValue = totalValue;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public List<String> getTotaltx() {
        return Totaltx;
    }

    public void setTotaltx(List<String> totaltx) {
        Totaltx = totaltx;
    }

    //块hash值
    private String hash;

//    //确认次数
//    private Integer confirms;

    //交易总数
    private String txCount;

    //总交易额
    private String totalValue;

    //时间戳
    private String time;

    //区块大小
    private Integer size;

    //挖矿奖励
    private String reward;

    //难度
    private String difficulty;

    //交易列表
    private List<String> Totaltx;


    private static final long serialVersionUID = 1L;
}