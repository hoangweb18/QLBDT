/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.model;

import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class NhanVien {
    private String manv;
    private String tennv;
    private String ngaysinh;
    private  String gioitinh;
    private int sodt;
    private String diachi;
    private String sogiolv;
    
    public String getMavn() {
        return manv;
    }
    public void setManv(String manv){
        this.manv=manv;
    }
    
    public String getTennv() {
        return tennv;
    }
    public void setTennv(String tennv){
        this.tennv=tennv;
    }
    
    public String getNgaysinh() {
        return ngaysinh;
    }
    public void setNgaysinh(String ngaysinh){
        this.ngaysinh=ngaysinh;
    }
    
    public String getGioitinh(){
        return gioitinh;
    }
    public void setGioitinh(String gioitinh){
        this.gioitinh=gioitinh;
    }
    
    public int getSodt(){
        return sodt;
    }
    public void setSodt(int sodt){
        this.sodt=sodt;
    }
    
    public String getDiachi(){
        return diachi;
    }
    public void setDiachi(String diachi){
        this.diachi=diachi;
    }
    
    public String getSogiolv(){
        return sogiolv;
    }
    public void setSogiolv(String sogiolv){
        this.sogiolv=sogiolv;
    }

    public NhanVien(String manv, String tennv, String ngaysinh, String gioitinh, int sodt, String diachi, String sogiolv) {
        this.manv = manv;
        this.tennv = tennv;
        this.ngaysinh = ngaysinh;
        this.gioitinh = gioitinh;
        this.sodt = sodt;
        this.diachi = diachi;
        this.sogiolv = sogiolv;
    }
    private static final Logger LOG = Logger.getLogger(NhanVien.class.getName());
    
}
