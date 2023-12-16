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
    
    public List<QuanLyBH> getAllList();
    
    public String getTenKhachHangByIdClient(int idClient);
}
