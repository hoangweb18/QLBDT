/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.dao;

import com.priot4all.quanlybandienthoai.model.QuanLyBH;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class QuanLyBHDAOImpl implements QuanLyBHDAO {

    @Override
    public List<QuanLyBH> getList() {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "SELECT b.idBill, b.idClient, t.nameSmartPhone, b.Date, t.price * b.quantityPurchased AS totalPrice "
                    + "FROM Bill b "
                    + "JOIN ThongTinDT t ON b.idSmartPhone = t.IdSmartPhone";
            List<QuanLyBH> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QuanLyBH bill = new QuanLyBH();
                bill.setIdBill(rs.getInt("idBill"));
                bill.setIdClient(rs.getInt("idClient"));
                bill.setNamesmartPhone(rs.getString("nameSmartPhone"));
                bill.setDate(rs.getString("Date"));
                bill.setTotalPrice(rs.getDouble("totalPrice"));

                list.add(bill);
            }
            ps.close();
            rs.close();
            cons.close();
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public List<QuanLyBH> getAllLists() {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "SELECT B.idBill, B.idClient, B.idSmartPhone, C.nameClient, C.numberPhone, "
                    + "T.nameSmartPhone, B.quantityPurchased, T.capacity, T.color, "
                    + "B.Date, T.price, B.quantityPurchased * T.price AS totalPrice "
                    + "FROM Bill B "
                    + "JOIN Customer C ON B.idClient = C.idClient "
                    + "JOIN ThongTinDT T ON B.idSmartPhone = T.IdSmartPhone";
            List<QuanLyBH> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                QuanLyBH bill = new QuanLyBH();
                bill.setIdBill(rs.getInt("idBill"));
                bill.setIdClient(rs.getInt("idClient"));
                bill.setIdSmartPhone(rs.getInt("idSmartPhone"));
                bill.setNameClient(rs.getString("nameClient"));
                bill.setNamesmartPhone(rs.getString("nameSmartPhone"));
                bill.setQuantityPurchased(rs.getInt("quantityPurchased"));
                bill.setCapacity(rs.getInt("capacity"));
                bill.setColor(rs.getString("color"));
                bill.setDate(rs.getString("Date"));
                bill.setPrice(rs.getInt(rs.getInt("price")));

                bill.setTotalPrice(rs.getDouble("totalPrice"));

                list.add(bill);
            }

            ps.close();
            rs.close();
            cons.close();
            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int create(QuanLyBH quanLyBH) {
        return 0;
    }

    @Override
    public int update(QuanLyBH quanLyBH) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int deleteRecord(QuanLyBH quanLyBH) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public String getTenKhachHangByIdClient(int idClient){
        String tenKhachHang = "";

    try {
        Connection cons = DBConnect.getConnection();
        String sql = "SELECT nameClient FROM Customer WHERE idClient = ?";
        PreparedStatement ps = cons.prepareCall(sql);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            tenKhachHang = rs.getString("nameClient");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } 
    return tenKhachHang;
    }
}
