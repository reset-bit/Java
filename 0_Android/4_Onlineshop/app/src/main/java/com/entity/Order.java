package com.entity;

public class Order {
    private String bname;
    private String label;
    private double bprice;
    private int num;

    public Order(String bname, String label, double bprice, int num) {
        this.bname = bname;
        this.label = label;
        this.bprice = bprice;
        this.num = num;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getBprice() {
        return bprice;
    }

    public void setBprice(double bprice) {
        this.bprice = bprice;
    }

    public String getLabel() {
        return label;
    }
}
