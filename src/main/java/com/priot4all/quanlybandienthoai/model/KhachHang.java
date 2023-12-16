/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.model;

/**
 *
 * @author admin
 */
public class KhachHang {
    private int idClient;
    private String nameClient;
    private String numberPhone;
    private String PurchaseStatus;

    public KhachHang(int idClient, String nameClient, String numberPhone, String PuschaseStatus) {
        this.idClient = idClient;
        this.nameClient = nameClient;
        this.numberPhone = numberPhone;
        this.PurchaseStatus = PuschaseStatus;
    }

    public KhachHang(){
        
    }
    
    
    
    
    public String getPurchaseStatus() {
        return PurchaseStatus;
    }

    public void setPurchaseStatus(String PurchaseStatus) {
        this.PurchaseStatus = PurchaseStatus;
    }
    
    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }
    
    
}
