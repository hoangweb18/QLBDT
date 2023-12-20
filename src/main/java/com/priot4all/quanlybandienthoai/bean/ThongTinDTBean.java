/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.bean;

/**
 *
 * @author admin
 */
public class ThongTinDTBean {
    private String mausac;
    private int Soluong;

    public ThongTinDTBean() {
    }

    public ThongTinDTBean(String mausac, int Soluong) {
        this.mausac = mausac;
        this.Soluong = Soluong;
    }

    public String getMausac() {
        return mausac;
    }

    public void setMausac(String mausac) {
        this.mausac = mausac;
    }

    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int Soluong) {
        this.Soluong = Soluong;
    }

}
