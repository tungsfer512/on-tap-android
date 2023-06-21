package com.example.test01.model;

public class SinhVienLop {
    Integer id;
    Integer sinhVienId;
    Integer lopId;

    public SinhVienLop(Integer id, Integer sinhVienId, Integer lopId) {
        this.id = id;
        this.sinhVienId = sinhVienId;
        this.lopId = lopId;
    }
    public SinhVienLop(Integer sinhVienId, Integer lopId) {
        this.sinhVienId = sinhVienId;
        this.lopId = lopId;
    }

    public SinhVienLop() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSinhVienId() {
        return sinhVienId;
    }

    public void setSinhVienId(Integer sinhVienId) {
        this.sinhVienId = sinhVienId;
    }

    public Integer getLopId() {
        return lopId;
    }

    public void setLopId(Integer lopId) {
        this.lopId = lopId;
    }
}
