package com.example.cscom_pc.phanmemkhaosat;

/**
 * Created by CSCOM-PC on 4/25/2017.
 */

public class DataListView
{
    String ten ;
    String ngay;
    String tenbv;

    public DataListView(String ten, String ngay, String tenbv) {
        this.ten = ten;
        this.ngay = ngay;
        this.tenbv = tenbv;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTenbv() {
        return tenbv;
    }

    public void setTenbv(String tenbv) {
        this.tenbv = tenbv;
    }
}
