/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.dao;

import com.priot4all.quanlybandienthoai.model.ThongTinDT;
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
public class ThongTinDTDAOImpl implements ThongTinDTDAO {

    @Override
    public List<ThongTinDT> getList() {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "select * from ThongTinDT";
            List<ThongTinDT> list = new ArrayList<>();
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()) {
                ThongTinDT thongTinDT = new ThongTinDT();
                thongTinDT.setIdSmartPhone(rs.getInt("IdSmartPhone"));
                thongTinDT.setNameSmartPhone(rs.getString("NameSmartPhone"));
                thongTinDT.setQuantityInventory(rs.getInt("quantityInventory"));
                thongTinDT.setPrice(rs.getDouble("price"));
                thongTinDT.setCategory(rs.getString("category"));
                thongTinDT.setCapacity(rs.getInt("capacity"));
                thongTinDT.setColor(rs.getString("color"));
                
                list.add(thongTinDT);
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
    
    public int createOrUpdate(ThongTinDT thongTinDT) {
        try {
            Connection cons = DBConnect.getConnection();
            // viết câu lệnh insert thông tin trong sql vào đây để chạy
            String sql = "INSERT INTO ThongTinDT(IdSmartPhone, nameSmartPhone, quantityInventory, price, category, capacity, color) VALUES(?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE  nameSmartPhone = VALUES(nameSmartPhone), quantityInventory = VALUES(quantityInventory), price = VALUES(price), category = VALUES(category), capacity = VALUES(capacity), color = VALUES(color);";
            PreparedStatement ps = cons.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);//tạo khóa tự động
            ps.setInt(1,thongTinDT.getIdSmartPhone());
            ps.setString(2, thongTinDT.getNameSmartPhone());
            ps.setInt(3,thongTinDT.getQuantityInventory());
            ps.setDouble(4, thongTinDT.getPrice());
            ps.setString(5, thongTinDT.getCategory());
            ps.setInt(6, thongTinDT.getCapacity());
            ps.setString(7, thongTinDT.getColor());
            // thêm các cái khác sau
            
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            int generatedKey = 0;
            if(rs.next()){
            generatedKey = rs.getInt(1);
            System.out.println("Đã chèn thành công. Khóa chính mới: " + generatedKey);
            }
            //System.out.println("Generated Key" + generatedKey); // bỏ dòng này
            
            
            
            ps.close();
            cons.close();
            return generatedKey;
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
    }
    
    public int deleteRecord(ThongTinDT thongTinDT) {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "DELETE FROM ThongTinDT WHERE IdSmartPhone = ?";
            PreparedStatement ps = cons.prepareStatement(sql);
            ps.setInt(1, thongTinDT.getIdSmartPhone());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Đã xóa record với IdSmartPhone: " + thongTinDT.getIdSmartPhone());
               
                
            } else {
                System.out.println("Không tìm thấy record với IdSmartPhone: " + thongTinDT.getIdSmartPhone());
            }

            ps.close();
            cons.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            return 0;
        }
        return 1;
    }

    
    public static void main(String[] args) {
        ThongTinDTDAO thongTinDTDAO = new ThongTinDTDAOImpl();
        System.out.println(thongTinDTDAO.getList());
    }
}
