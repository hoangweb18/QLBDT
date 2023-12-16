/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.dao;

import com.priot4all.quanlybandienthoai.model.KhachHang;
import java.util.List;

/**
 *
 * @author admin
 */
public interface KhachHangDAO {
    
    public List<KhachHang> getList();
    
    public int createOrUpdate(KhachHang khachHang);
    
    public int deleteRecord(KhachHang khachHang);
}
