package com.example.assignmentandroidnetworking.DTO;

public class Foods {
    int id;
    String img;
    String nameFoods;
    Integer time;
    int price,orderf;

    public int getOrderf() {
        return orderf;
    }

    public void setOrderf(int orderf) {
        this.orderf = orderf;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNameFoods() {
        return nameFoods;
    }

    public void setNameFoods(String nameFoods) {
        this.nameFoods = nameFoods;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Foods() {
    }

    public Foods(int id, String img, String nameFoods, Integer time, int price, int orderf) {
        this.id = id;
        this.img = img;
        this.nameFoods = nameFoods;
        this.time = time;
        this.price = price;
        this.orderf = orderf;
    }
}
