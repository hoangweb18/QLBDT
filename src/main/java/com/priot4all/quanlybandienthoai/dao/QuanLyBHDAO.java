/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.dao;

import com.priot4all.quanlybandienthoai.model.QuanLyBH;
import java.util.List;

/**
 *
 * @author admin
 */
public interface QuanLyBHDAO {
     
    public List<QuanLyBH> getList();
    
    public List<QuanLyBH> getAllLists();
    
    public int create(QuanLyBH quanLyBH);
    
    public int update(QuanLyBH quanLyBH);
    
    public int deleteRecord(QuanLyBH quanLyBH);
    
    public String getTenKhachHangByIdClient(int idClient); 
}