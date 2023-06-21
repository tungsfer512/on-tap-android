package com.example.test01.model;

public class SinhVien {
    Integer id;
    String name;
    Integer dob;
    String address;
    String namhoc;

    public SinhVien() {
    }

    public SinhVien(Integer id, String name, Integer dob, String address, String namhoc) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.namhoc = namhoc;
    }public SinhVien(String name, Integer dob, String address, String namhoc) {
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.namhoc = namhoc;
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

    public Integer getDob() {
        return dob;
    }

    public void setDob(Integer dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNamhoc() {
        return namhoc;
    }

    public void setNamhoc(String namhoc) {
        this.namhoc = namhoc;
    }

    @Override
    public String toString() {
        return id + "_" + name;
    }
}