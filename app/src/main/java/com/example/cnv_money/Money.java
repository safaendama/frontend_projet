package com.example.cnv_money;

import com.google.gson.annotations.SerializedName;

public class Money {
    @SerializedName("amount")
    private Double amount;

    @SerializedName("from")
    private String from;

    @SerializedName("to")
    private String to;

    public Money(Double amount, String from, String to) {
        this.amount = amount;
        this.from = from;
        this.to = to;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
