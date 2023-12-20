/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.model;

/**
 *
 * @author admin
 */
public class TaiKhoan {
    private int matk;
    private String tendangnhap;
    private String matkhau;
    private boolean tinhtrang;

    public TaiKhoan(int matk, String tendangnhap, String matkhau, boolean tinhtrang) {
        this.matk = matk;
        this.tendangnhap = tendangnhap;
        this.matkhau = matkhau;
        this.tinhtrang = tinhtrang;
    }

    public TaiKhoan() {
    }

    public int getMatk() {
        return matk;
    }

    public void setMatk(int matk) {
        this.matk = matk;
    }

    public String getTendangnhap() {
        return tendangnhap;
    }

    public void setTendangnhap(String tendangnhap) {
        this.tendangnhap = tendangnhap;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public boolean isTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(boolean tinhtrang) {
        this.tinhtrang = tinhtrang;
    }
    
    
}
