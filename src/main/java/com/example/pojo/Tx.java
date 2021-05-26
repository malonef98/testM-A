package com.example.pojo;
import lombok.Data;
import java.io.Serializable;

/**
 * tx
 * @author 
 */
@Data
public class Tx implements Serializable {


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
    private String desc;

    //交易类型
    private String dataType;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }



    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getBlockHeight() {
        return blockHeight;
    }

    public void setBlockHeight(Integer blockHeight) {
        this.blockHeight = blockHeight;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(String gasPrice) {
        this.gasPrice = gasPrice;
    }

    public String getGasUsed() {
        return gasUsed;
    }

    public void setGasUsed(String gasUsed) {
        this.gasUsed = gasUsed;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
//    private List<TxnInput> txnInput;

//    private List<TxnOutput> txnOutput;

    private static final long serialVersionUID = 1L;
}