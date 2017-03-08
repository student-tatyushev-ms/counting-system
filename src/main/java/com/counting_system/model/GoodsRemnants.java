package com.counting_system.model;

import java.sql.Date;

public class GoodsRemnants {

    private int id;
    private Date date;
    private int number;
    private int sum;
    private Contractor contractor;
    private GoodsReceipt goodsReceipt;

    public GoodsRemnants(int id, Date date, int number, int sum, Contractor contractor, GoodsReceipt goodsReceipt) {
        this.id = id;
        this.date = date;
        this.number = number;
        this.sum = sum;
        this.contractor = contractor;
        this.goodsReceipt = goodsReceipt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return date;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public int getSum() {
        return sum;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setGoodsReceipt(GoodsReceipt goodsReceipt) {
        this.goodsReceipt = goodsReceipt;
    }

    public GoodsReceipt getGoodsReceipt() {
        return goodsReceipt;
    }

}
