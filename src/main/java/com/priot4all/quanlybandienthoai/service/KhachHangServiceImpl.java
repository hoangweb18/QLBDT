/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.service;

import com.priot4all.quanlybandienthoai.dao.KhachHangDAO;
import com.priot4all.quanlybandienthoai.dao.KhachHangDAOImpl;
import com.priot4all.quanlybandienthoai.model.KhachHang;
import java.util.List;

/**
 *
 * @author admin
 */
public class KhachHangServiceImpl implements KhachHangService {
    
    private KhachHangDAO khachHangDAO = null;
    
    public KhachHangServiceImpl () {
       khachHangDAO = new KhachHangDAOImpl();
    }

    @Override
    public List<KhachHang> getList() {
        return khachHangDAO.getList();
    }

    @Override
    public int createOrUpdate(KhachHang khachHang){
        return khachHangDAO.createOrUpdate(khachHang);
    }
    @Override
    public int deleteRecord(KhachHang khachHang){
        return khachHangDAO.deleteRecord(khachHang);
    }
}
