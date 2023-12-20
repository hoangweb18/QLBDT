/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.dao;

import com.priot4all.quanlybandienthoai.bean.QuanLyBHBean;
import com.priot4all.quanlybandienthoai.bean.ThongTinDTBean;
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
public class TrangChuDAOImpl implements TrangChuDao {

    @Override
    public List<ThongTinDTBean> getListByThongTinDT() {
        try {
        Connection cons = DBConnect.getConnection();
        String sql = "SELECT color, quantityInventory FROM ThongTinDT";
        List<ThongTinDTBean> list = new ArrayList<>();
        PreparedStatement ps = cons.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ThongTinDTBean thongTinDTBean = new ThongTinDTBean();
            thongTinDTBean.setMausac(rs.getString("color"));
            thongTinDTBean.setSoluong(rs.getInt("quantityInventory"));

            list.add(thongTinDTBean);
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
    
    @Override
    public List<QuanLyBHBean> getListByQuanLyBHMonth() {
    try {
        Connection cons = DBConnect.getConnection();
        String sql = "SELECT " +
                         "DATE_FORMAT(Date, '%Y-%m') AS YearMonth, " +
                         "SUM(quantityPurchased) AS TotalQuantity, " +
                         "SUM(quantityPurchased * (SELECT price FROM ThongTinDT WHERE IdSmartPhone = Bill.idSmartPhone)) AS TotalRevenue " +
                         "FROM Bill " +
                         "GROUP BY DATE_FORMAT(Date, '%Y-%m') " +
                         "ORDER BY YearMonth";
        
        List<QuanLyBHBean> list = new ArrayList<>();
        PreparedStatement ps = cons.prepareCall(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            QuanLyBHBean quanLyBHBean = new QuanLyBHBean();
            quanLyBHBean.setYearmonth(rs.getString("YearMonth"));
            quanLyBHBean.setSoluongban(rs.getInt("TotalQuantity"));
            quanLyBHBean.setDoanhThu(rs.getDouble("TotalRevenue"));

            list.add(quanLyBHBean);
        }

        rs.close();
        ps.close();
        cons.close();
        return list;
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return null;
}
    
    @Override
    public List<QuanLyBHBean> getListByQuanLyBHYear() {
    try {
        Connection cons = DBConnect.getConnection();
        String sql = "SELECT " +
                     "MONTH(Date) AS Month, " +
                     "SUM(quantityPurchased) AS TotalQuantity, " +
                     "SUM(quantityPurchased * (SELECT price FROM ThongTinDT WHERE IdSmartPhone = Bill.idSmartPhone)) AS TotalRevenue " +
                     "FROM Bill " +
                     "GROUP BY MONTH(Date)";
        
        List<QuanLyBHBean> list = new ArrayList<>();
        PreparedStatement ps = cons.prepareCall(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            QuanLyBHBean quanLyBHBean = new QuanLyBHBean();
            quanLyBHBean.setMonth(rs.getInt("Month"));
            quanLyBHBean.setSoluongban(rs.getInt("TotalQuantity"));
            quanLyBHBean.setDoanhThu(rs.getDouble("TotalRevenue"));

            list.add(quanLyBHBean);
        }

        rs.close();
        ps.close();
        cons.close();
        return list;
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return null;
}
    
    
    public int getQuantityInventory() {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "SELECT SUM(quantityInventory) AS totalQuantity FROM ThongTinDT";
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int totalQuantity = rs.getInt("totalQuantity");
                ps.close();
                rs.close();
                cons.close();
                return totalQuantity;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    public double getTotalRevenue() {
        try {
            Connection cons = DBConnect.getConnection();
            String sql = "SELECT SUM(t.price * b.quantityPurchased) AS totalRevenue " +
                     "FROM Bill b " +
                     "JOIN ThongTinDT t ON b.idSmartPhone = t.IdSmartPhone";
            PreparedStatement ps = cons.prepareCall(sql);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                double totalRevenue = rs.getDouble("totalRevenue");
                ps.close();
                rs.close();
                cons.close();
                return totalRevenue;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    
    public int getTotalOrders() {
        try {
            Connection connection = DBConnect.getConnection();
            String sql = "SELECT COUNT(*) AS totalOrders " +
                         "FROM Bill";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int totalOrders = resultSet.getInt("totalOrders");
                preparedStatement.close();
                resultSet.close();
                connection.close();
                return totalOrders;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    
    public int getTotalCustomers() {
        try {
            Connection connection = DBConnect.getConnection();
            String sql = "SELECT COUNT(*) AS totalCustomers FROM Customer";
            PreparedStatement preparedStatement = connection.prepareCall(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int totalCustomers = resultSet.getInt("totalCustomers");
                preparedStatement.close();
                resultSet.close();
                connection.close();
                return totalCustomers;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return 0;
    }
    
}
