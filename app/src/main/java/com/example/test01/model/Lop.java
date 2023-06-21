package com.example.test01.model;

public class Lop {
    Integer id;
    String name;
    String mota;

    public Lop() {
    }

    public Lop(Integer id, String name, String mota) {
        this.id = id;
        this.name = name;
        this.mota = mota;
    }

    public Lop(String name, String mota) {
        this.name = name;
        this.mota = mota;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    @Override
    public String toString() {
        return id + "_" + name;
    }
}
