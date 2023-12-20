/*0
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.model;

import java.io.Serializable;
import java.sql.Date;



/**
 *
 * @author admin
 */
public class QuanLyBH implements Serializable {
    private int idBill;
    private int idClient;
    private int idSmartPhone;
    private String nameClient;
    private String NumberPhoneClient;
    private String namesmartPhone;
    private int quantityPurchased;
    private String category;
    private int capacity;
    private String color;
    private Date Date;
    private double price;
    private double totalPrice;

    public QuanLyBH(int idBill, int idClient, int idSmartPhone, String nameClient, String NumberPhoneClient, String namesmartPhone, int quantityPurchased,String category, int capacity, String color, Date Date, double price, double totalPrice) {
        this.idBill = idBill;
        this.idClient = idClient;
        this.idSmartPhone = idSmartPhone;
        this.nameClient = nameClient;
        this.NumberPhoneClient = NumberPhoneClient;
        this.namesmartPhone = namesmartPhone;
        this.quantityPurchased = quantityPurchased;
        this.category = category;
        this.capacity = capacity;
        this.color = color;
        this.Date = Date;
        this.price = price;
        this.totalPrice = totalPrice;
    }

    

    public QuanLyBH() {
        
    }


    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getNamesmartPhone() {
        return namesmartPhone;
    }

    public void setNamesmartPhone(String namesmartPhone) {
        this.namesmartPhone = namesmartPhone;
    }

    public Date getDate() {
        return Date;
    }

    public void setDate(Date Date) {
        this.Date = Date;
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getQuantityPurchased() {
        return quantityPurchased;
    }

    public void setQuantityPurchased(int quantityPurchased) {
        this.quantityPurchased = quantityPurchased;
    }

    public int getIdSmartPhone() {
        return idSmartPhone;
    }

    public void setIdSmartPhone(int idSmartPhone) {
        this.idSmartPhone = idSmartPhone;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getNumberPhoneClient() {
        return NumberPhoneClient;
    }

    public void setNumberPhoneClient(String NumberPhoneClient) {
        this.NumberPhoneClient = NumberPhoneClient;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
    
}
