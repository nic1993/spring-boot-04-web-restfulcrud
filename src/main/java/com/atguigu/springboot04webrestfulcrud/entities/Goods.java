package com.atguigu.springboot04webrestfulcrud.entities;

public class Goods {
    private Integer id;
    private  String sellername;
    private  String goodsname;
    private  Integer price;
    private  Integer sellnums;
    private  String location;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSellername() {
        return sellername;
    }

    public void setSellername(String sellername) {
        this.sellername = sellername;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getSellnums() {
        return sellnums;
    }

    public void setSellnums(Integer sellnums) {
        this.sellnums = sellnums;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
