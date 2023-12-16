/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.service;

import com.priot4all.quanlybandienthoai.dao.QuanLyBHDAO;
import com.priot4all.quanlybandienthoai.dao.QuanLyBHDAOImpl;
import com.priot4all.quanlybandienthoai.model.QuanLyBH;
import java.util.List;

/**
 *
 * @author admin
 */
public class QuanLyBHServiceImpl implements QuanLyBHService {
    
    private QuanLyBHDAO quanLyBHDAO = null;
    
    public QuanLyBHServiceImpl () {
       quanLyBHDAO = new QuanLyBHDAOImpl();
    }

    @Override
    public List<QuanLyBH> getList() {
        return quanLyBHDAO.getList();
    }
    @Override
    public List<QuanLyBH> getAllList() {
        return quanLyBHDAO.getList();
    }
    
    @Override
    public String getTenKhachHangByIdClient(int idClient) {
        return quanLyBHDAO.getTenKhachHangByIdClient(idClient);
    }
}
