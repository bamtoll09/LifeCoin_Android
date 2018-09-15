package caa.kr.lifecoin.retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Data implements Serializable
{

    @SerializedName("transactions")
    @Expose
    private List<Transaction> transactions = null;
    @SerializedName("proof-of-work")
    @Expose
    private Integer proofOfWork;
    private final static long serialVersionUID = -6088608109622986438L;

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Integer getProofOfWork() {
        return proofOfWork;
    }

    public void setProofOfWork(Integer proofOfWork) {
        this.proofOfWork = proofOfWork;
    }

}