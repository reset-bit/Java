package com.entity;

public class Book {
    int id;
    String bname;
    String label;
    double bprice;
    String btype;
    String author;
    String publish;

    public Book() {
    }

    public Book(int id, String bname, String label, double bprice, String btype, String author, String publish) {
        this.id = id;
        this.bname = bname;
        this.label = label;
        this.bprice = bprice;
        this.btype = btype;
        this.author = author;
        this.publish = publish;
    }

    public Book(String bname, String label, double bprice, String btype, String author, String publish) {
        this.bname = bname;
        this.label = label;
        this.bprice = bprice;
        this.btype = btype;
        this.author = author;
        this.publish = publish;
    }

    public Book(String bname, double bprice) {
        this.bname = bname;
        this.bprice = bprice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getBprice() {
        return bprice;
    }

    public void setBprice(double bprice) {
        this.bprice = bprice;
    }

    public String getBtype() {
        return btype;
    }

    public void setBtype(String btype) {
        this.btype = btype;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }
}
