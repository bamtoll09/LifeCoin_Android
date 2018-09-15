package caa.kr.lifecoin.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Transaction implements Serializable
{

    @SerializedName("to")
    @Expose
    private String to;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("from")
    @Expose
    private String from;
    private final static long serialVersionUID = -8226446209166658042L;

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

}