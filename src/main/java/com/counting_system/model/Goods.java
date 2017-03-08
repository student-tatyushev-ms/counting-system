package com.counting_system.model;

public class Goods {

    private int id;
    private int number;
    private int prise;
    private int cost;
    private Nomenclature nomenclature;
    private GoodsReceipt goodsReceipt;

    public Goods(int id, int number, int prise, int cost, Nomenclature nomenclature, GoodsReceipt goodsReceipt) {
        this.id = id;
        this.number = number;
        this.prise = prise;
        this.cost = cost;
        this.nomenclature = nomenclature;
        this.goodsReceipt = goodsReceipt;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setGoodsReceipt(GoodsReceipt goodsReceipt) {
        this.goodsReceipt = goodsReceipt;
    }

    public GoodsReceipt getGoodsReceipt() {
        return this.goodsReceipt;
    }

    public void setNomenclature(Nomenclature nomenclature) {
        this.nomenclature = nomenclature;
    }

    public Nomenclature getNomenclature() {
        return nomenclature;
    }

    public void setPrise(int prise) {
        this.prise = prise;
    }

    public int getPrise() {
        return prise;
    }

}
