package com.counting_system.model;

import java.sql.Date;

public class GoodsReceipt {

    private int id;
    private Date date;
    private Contractor contractor;
    private boolean status;

    public GoodsReceipt(int id, Date date, Contractor contractor, boolean status) {
        this.id = id;
        this.date = date;
        this.contractor = contractor;
        this.status = status;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public Contractor getContractor() {
        return this.contractor;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getDate() {
        return this.date;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return this.status;
    }

}
