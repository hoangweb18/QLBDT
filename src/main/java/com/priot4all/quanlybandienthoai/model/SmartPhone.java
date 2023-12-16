/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.model;

/**
 *
 * @author admin
 */
public class SmartPhone {
    private QuanLyBH nameSmartPhone;
    private int quantityPurchased;

    public SmartPhone(QuanLyBH nameSmartPhone, int quantityPurchased) {
        this.nameSmartPhone = nameSmartPhone;
        this.quantityPurchased = quantityPurchased;
    }

    public QuanLyBH getNameSmartPhone() {
        return nameSmartPhone;
    }

    public void setNameSmartPhone(QuanLyBH nameSmartPhone) {
        this.nameSmartPhone = nameSmartPhone;
    }

    public int getQuantityPurchased() {
        return quantityPurchased;
    }

    public void setQuantityPurchased(int quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }
    
}
