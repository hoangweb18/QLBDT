/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.service;

import com.priot4all.quanlybandienthoai.bean.QuanLyBHBean;
import com.priot4all.quanlybandienthoai.bean.ThongTinDTBean;
import com.priot4all.quanlybandienthoai.dao.ThongTinDTDAO;
import com.priot4all.quanlybandienthoai.dao.ThongTinDTDAOImpl;
import com.priot4all.quanlybandienthoai.dao.TrangChuDAOImpl;
import com.priot4all.quanlybandienthoai.dao.TrangChuDao;
import java.util.List;

/**
 *
 * @author admin
 */
public class TrangChuServiceImpl implements TrangChuService {
    
    private TrangChuDao trangChuDao = null;
    
    public TrangChuServiceImpl() {
        trangChuDao = new TrangChuDAOImpl();
    }

    @Override
    public List<ThongTinDTBean> getListByThongTinDT(){
        return trangChuDao.getListByThongTinDT();
    }
    
    @Override
    public List<QuanLyBHBean> getListByQuanLyBHMonth(){
        return trangChuDao.getListByQuanLyBHMonth();
    }
    
    @Override
    public List<QuanLyBHBean> getListByQuanLyBHYear(){
        return trangChuDao.getListByQuanLyBHYear();
    }
    
    @Override
    public int getQuantityInventory() {
        return trangChuDao.getQuantityInventory();
    }
    
    @Override
    public double getTotalRevenue() {
        return trangChuDao.getTotalRevenue();
    }
    
    @Override
    public int getTotalOrders(){
        return trangChuDao.getTotalOrders();
    }
    
    @Override
    public int getTotalCustomers() {
        return trangChuDao.getTotalCustomers();
    }
}
