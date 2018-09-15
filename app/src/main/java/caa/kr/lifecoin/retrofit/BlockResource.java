package caa.kr.lifecoin.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BlockResource implements Serializable
{

    @SerializedName("index")
    @Expose
    private Integer index;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("hash")
    @Expose
    private String hash;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    private final static long serialVersionUID = -1099170518230091167L;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

}