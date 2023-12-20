/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.service;

import com.priot4all.quanlybandienthoai.model.QuanLyBH;
import java.util.List;

/**
 *
 * @author admin
 */
public interface QuanLyBHService {
    
    public List<QuanLyBH> getList();
    
    public List<QuanLyBH> getAllLists();
    
    public List<Integer> getIdsSmartPhoneList();
    
    public List<Integer> getIdClientList();
    
    public int create(QuanLyBH quanLyBH);
    
    public int createBill(QuanLyBH quanLyBH);
    
    public int updateBill(QuanLyBH quanLyBH);
    
    public int deleteRecord(QuanLyBH quanLyBH);
}
