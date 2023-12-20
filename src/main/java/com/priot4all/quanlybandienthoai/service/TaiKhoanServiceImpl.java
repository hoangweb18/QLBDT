/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.service;

import com.priot4all.quanlybandienthoai.dao.TaiKhoanDAO;
import com.priot4all.quanlybandienthoai.dao.TaiKhoanDAOImpl;
import com.priot4all.quanlybandienthoai.model.TaiKhoan;

/**
 *
 * @author admin
 */
public class TaiKhoanServiceImpl implements TaiKhoanService {
    
    private TaiKhoanDAO taiKhoanDAO = null;

    public TaiKhoanServiceImpl() {
        taiKhoanDAO = new TaiKhoanDAOImpl();
    }

    @Override
    public TaiKhoan login(String tenDangNhap, String matKhau) {
        return taiKhoanDAO.login(tenDangNhap, matKhau);
    }
}
