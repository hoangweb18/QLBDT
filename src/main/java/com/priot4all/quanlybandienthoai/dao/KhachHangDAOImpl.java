/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.dao;

import com.priot4all.quanlybandienthoai.model.KhachHang;
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
public class KhachHangDAOImpl implements KhachHangDAO {
    
     @Override
    public List<KhachHang> getList() {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "SELECT DISTINCT c.idClient, c.nameClient, c.numberPhone, " +
             "CASE WHEN b.idClient IS NOT NULL THEN 'Đã mua' ELSE 'Chưa mua' END AS PurchaseStatus " +
             "FROM Customer c " +
             "LEFT JOIN Bill b ON c.idClient = b.idClient";
            List<KhachHang> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                KhachHang customer = new KhachHang();
                customer.setIdClient(rs.getInt("idClient"));
                customer.setNameClient(rs.getString("nameClient"));
                customer.setNumberPhone(rs.getString("numberPhone"));
                customer.setPurchaseStatus(rs.getString("PurchaseStatus"));

                list.add(customer);
            }
            ps.close();
            rs.close();
            cons.close();
            return list;
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
       public int createOrUpdate(KhachHang khachHang) {
        try {
        Connection cons = DBConnect.getConnection();
        // Viết câu lệnh insert/update thông tin trong SQL vào đây để chạy
        String sql = "INSERT INTO Customer (idClient, nameClient, numberPhone) VALUES(?, ?, ?) ON DUPLICATE KEY UPDATE nameClient = VALUES(nameClient), numberPhone = VALUES(numberPhone)";
        PreparedStatement ps = cons.prepareStatement(sql);
        ps.setInt(1, khachHang.getIdClient());
        ps.setString(2, khachHang.getNameClient());
        ps.setString(3, khachHang.getNumberPhone());
        // Thêm các cột khác sau

        int rowsAffected = ps.executeUpdate();

        ps.close();
        cons.close();

        return rowsAffected;
    } catch (Exception ex) {
        ex.printStackTrace();
    }
    return 0;
}
       
       
       
       public int deleteRecord(KhachHang khachHang) {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "DELETE FROM Customer WHERE idClient = ?";
            PreparedStatement ps = cons.prepareStatement(sql);
            ps.setInt(1, khachHang.getIdClient());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Đã xóa record với idClient: " + khachHang.getIdClient());
            } else {
                System.out.println("Không tìm thấy record với idClient: " + khachHang.getIdClient());
            }

            ps.close();
            cons.close();
            return rowsAffected; // Trả về số dòng bị ảnh hưởng bởi câu lệnh DELETE
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return 0;
    }
}
    

