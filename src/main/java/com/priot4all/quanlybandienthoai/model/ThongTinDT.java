/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.model;

/**
 *
 * @author admin
 */
public class ThongTinDT {
    private int IdSmartPhone;
    private String nameSmartPhone;
    private int quantityInventory;
    private double price;
    private String category;
    private int capacity;
    private String color;

    public ThongTinDT(int IdSmartPhone, String nameSmartPhone, int quantityInventory, double price, String category, int capacity, String color) {
        this.IdSmartPhone = IdSmartPhone;
        this.nameSmartPhone = nameSmartPhone;
        this.quantityInventory = quantityInventory;
        this.price = price;
        this.category = category;
        this.capacity = capacity;
        this.color = color;
    }

    public ThongTinDT() {
    }
    

    public int getIdSmartPhone() {
        return IdSmartPhone;
    }

    public void setIdSmartPhone(int IdSmartPhone) {
        this.IdSmartPhone = IdSmartPhone;
    }

    public String getNameSmartPhone() {
        return nameSmartPhone;
    }

    public void setNameSmartPhone(String nameSmartPhone) {
        this.nameSmartPhone = nameSmartPhone;
    }

    public int getQuantityInventory() {
        return quantityInventory;
    }

    public void setQuantityInventory(int quantityInventory) {
        this.quantityInventory = quantityInventory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return IdSmartPhone + " - " + nameSmartPhone;
    }

    
}
