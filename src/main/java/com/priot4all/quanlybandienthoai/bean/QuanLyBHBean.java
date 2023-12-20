/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.bean;

import java.sql.Date;



/**
 *
 * @author admin
 */
public class QuanLyBHBean {
    private int Soluongban;
    private double DoanhThu;
    private int Month;
    private int year;
    private String yearmonth;

    public QuanLyBHBean(int Soluongban, double DoanhThu, int Month, int year, String yearmonth) {
        this.Soluongban = Soluongban;
        this.DoanhThu = DoanhThu;
        this.Month = Month;
        this.year = year;
        this.yearmonth = yearmonth;
    }

    public QuanLyBHBean() {
    }

    public String getYearmonth() {
        return yearmonth;
    }

    public void setYearmonth(String yearmonth) {
        this.yearmonth = yearmonth;
    }
    
    public int getSoluongban() {
        return Soluongban;
    }

    public void setSoluongban(int Soluongban) {
        this.Soluongban = Soluongban;
    }

    public double getDoanhThu() {
        return DoanhThu;
    }

    public void setDoanhThu(double DoanhThu) {
        this.DoanhThu = DoanhThu;
    }

    public int getMonth() {
        return Month;
    }

    public void setMonth(int Month) {
        this.Month = Month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
    
}
