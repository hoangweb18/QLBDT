/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.dao;

import com.priot4all.quanlybandienthoai.model.TaiKhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author admin
 */
public class TaiKhoanDAOImpl implements TaiKhoanDAO {
    @Override
    public TaiKhoan login(String tenDangNhap, String matKhau) {
        Connection cons = DBConnect.getConnection();
        String sql = "SELECT * FROM TaiKhoan WHERE tendangnhap LIKE ? AND matkhau LIKE ?";
        TaiKhoan taiKhoan = null;
        try {
            PreparedStatement ps = (PreparedStatement) cons.prepareStatement(sql);
            ps.setString(1, tenDangNhap);
            ps.setString(2, matKhau);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                taiKhoan = new TaiKhoan();
                taiKhoan.setMatk(rs.getInt("matk"));
                taiKhoan.setTendangnhap(rs.getString("tendangnhap"));
                taiKhoan.setMatkhau(rs.getString("matkhau"));
                taiKhoan.setTinhtrang(rs.getBoolean("tinhtrang"));
            }
            ps.close();
            cons.close();
            return taiKhoan;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
