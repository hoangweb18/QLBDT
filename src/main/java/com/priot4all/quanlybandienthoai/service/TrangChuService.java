/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.service;

import com.priot4all.quanlybandienthoai.bean.QuanLyBHBean;
import com.priot4all.quanlybandienthoai.bean.ThongTinDTBean;
import java.util.List;

/**
 *
 * @author admin
 */
public interface TrangChuService {
    
    public List<ThongTinDTBean> getListByThongTinDT();
    
    public List<QuanLyBHBean> getListByQuanLyBHMonth();
    
    public List<QuanLyBHBean> getListByQuanLyBHYear();
    
    public int getQuantityInventory();
    
    public double getTotalRevenue();
    
    public int getTotalOrders();
    
    public int getTotalCustomers();
}
