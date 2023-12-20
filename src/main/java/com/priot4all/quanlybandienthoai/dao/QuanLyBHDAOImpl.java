/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.dao;

import com.priot4all.quanlybandienthoai.model.QuanLyBH;
import com.priot4all.quanlybandienthoai.service.QuanLyBHService;
import java.sql.Connection;
import java.sql.Date;
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
                bill.setDate(rs.getDate("Date"));
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
            String sql = "SELECT B.idBill, B.idClient, B.idSmartPhone,T.category, C.nameClient, C.numberPhone, "
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
                bill.setNumberPhoneClient(rs.getString("numberPhone"));
                bill.setNamesmartPhone(rs.getString("nameSmartPhone"));
                bill.setQuantityPurchased(rs.getInt("quantityPurchased"));
                bill.setCapacity(rs.getInt("capacity"));
                bill.setColor(rs.getString("color"));
                bill.setDate(rs.getDate("Date"));
                bill.setPrice(rs.getDouble("price"));
                bill.setCategory(rs.getString("category"));

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

    public List<Integer> getIdsSmartPhoneList() {
    List<Integer> idsSmartPhoneList = new ArrayList<>();

    try {
        Connection connection = DBConnect.getConnection();
        String sql = "SELECT IdSmartPhone FROM ThongTinDT";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int idSmartPhone = resultSet.getInt("IdSmartPhone");
            idsSmartPhoneList.add(idSmartPhone);
        }

        // Đóng tất cả các tài nguyên
        resultSet.close();
        preparedStatement.close();
        connection.close();
    } catch (SQLException e) {
        e.printStackTrace();
        // Xử lý ngoại lệ nếu cần thiết
    }

    return idsSmartPhoneList;
}
    
    public List<Integer> getIdClientList() {
    List<Integer> idClientList = new ArrayList<>();

    try {
        Connection connection = DBConnect.getConnection();
        String sql = "SELECT idClient FROM Customer";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            int idClient = resultSet.getInt("idClient");
            idClientList.add(idClient);
        }

        // Đóng tất cả các tài nguyên
        resultSet.close();
        preparedStatement.close();
        connection.close();
    } catch (SQLException e) {
        e.printStackTrace();
        // Xử lý ngoại lệ nếu cần thiết
    }

    return idClientList;
}

public int create(QuanLyBH quanLyBH) {
    try {
        Connection cons = DBConnect.getConnection();

        // Thêm thông tin vào bảng Customer
        String insertCustomerSQL = "INSERT INTO Customer (idClient, nameClient, numberPhone) VALUES (?, ?, ?)";
        PreparedStatement customerPS = cons.prepareStatement(insertCustomerSQL);
        customerPS.setInt(1, quanLyBH.getIdClient());
        customerPS.setString(2, quanLyBH.getNameClient());
        customerPS.setString(3, quanLyBH.getNumberPhoneClient());
        customerPS.executeUpdate();
        customerPS.close();

        // Thêm thông tin vào bảng Bill với khóa tự động
        String insertBillSQL = "INSERT INTO Bill (idClient, idSmartPhone, Date, quantityPurchased) VALUES (?, ?, ?, ?)";
        PreparedStatement billPS = cons.prepareStatement(insertBillSQL, PreparedStatement.RETURN_GENERATED_KEYS);
        billPS.setInt(1, quanLyBH.getIdClient());
        billPS.setInt(2, quanLyBH.getIdSmartPhone());
        billPS.setDate(3, quanLyBH.getDate());
        billPS.setInt(4, quanLyBH.getQuantityPurchased());
        
        billPS.execute();
        ResultSet rs = billPS.getGeneratedKeys();
        int rowsAffected = 0;
        if(rs.next()){
            rowsAffected = rs.getInt(1);
            System.out.println("Đã chèn thành công. Khóa chính mới: " + rowsAffected);
            updateInventory(cons, rowsAffected);
        }
        
        billPS.close();
        cons.close();
        return rowsAffected;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
}

public int updateBill(QuanLyBH quanLyBH) {
    try {
        Connection cons = DBConnect.getConnection();
        // Tìm tổng quantityPurchased theo idSmartPhone của hóa đơn
                String updateInventorySQL = "UPDATE ThongTinDT tt " +
                                            "JOIN (SELECT idSmartPhone, SUM(quantityPurchased) AS totalQuantity " +
                                                  "FROM Bill " +
                                                  "WHERE idBill = ? " +
                                                  "GROUP BY idSmartPhone) b " +
                                            "ON tt.IdSmartPhone = b.idSmartPhone " +
                                            "SET tt.quantityInventory = tt.quantityInventory + COALESCE(b.totalQuantity, 0)";
                PreparedStatement updateInventoryPS = cons.prepareStatement(updateInventorySQL);
                updateInventoryPS.setInt(1, quanLyBH.getIdBill());
                updateInventoryPS.executeUpdate();
                updateInventoryPS.close();

                
        // Cập nhật thông tin trong bảng Bill
        String updateBillSQL = "UPDATE Bill SET idClient = ?, idSmartPhone = ?, Date = ?, quantityPurchased = ? WHERE idBill = ?";
        PreparedStatement updateBillPS = cons.prepareStatement(updateBillSQL);
        updateBillPS.setInt(1, quanLyBH.getIdClient());
        updateBillPS.setInt(2, quanLyBH.getIdSmartPhone());
        updateBillPS.setDate(3, quanLyBH.getDate());
        updateBillPS.setInt(4, quanLyBH.getQuantityPurchased());
        updateBillPS.setInt(5, quanLyBH.getIdBill());

        int rowsAffected = updateBillPS.executeUpdate();
        updateInventory(cons, quanLyBH.getIdBill());
        updateBillPS.close();
        cons.close();

        return rowsAffected;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
}

// cập nhật số lượng theo kho dữ liệu
private void updateInventory(Connection cons, int newBillId) throws SQLException {
    String updateInventorySQL = "UPDATE ThongTinDT tt " +
                                "JOIN (SELECT idSmartPhone, SUM(quantityPurchased) AS totalQuantity " +
                                      "FROM Bill WHERE idBill = ? GROUP BY idSmartPhone) b " +
                                "ON tt.IdSmartPhone = b.idSmartPhone " +
                                "SET tt.quantityInventory = tt.quantityInventory - COALESCE(b.totalQuantity, 0)";
    PreparedStatement updateInventoryPS = cons.prepareStatement(updateInventorySQL);
    updateInventoryPS.setInt(1, newBillId);
    updateInventoryPS.executeUpdate();
    updateInventoryPS.close();
}

    @Override
    public int createBill(QuanLyBH quanLyBH) {
        try {
            Connection cons = DBConnect.getConnection();
            // Thêm thông tin vào bảng Bill với khóa tự động
            String insertBillSQL = "INSERT INTO Bill (idClient, idSmartPhone, Date, quantityPurchased) VALUES (?, ?, ?, ?)";
            PreparedStatement billPS = cons.prepareStatement(insertBillSQL, PreparedStatement.RETURN_GENERATED_KEYS);
            billPS.setInt(1, quanLyBH.getIdClient());
            billPS.setInt(2, quanLyBH.getIdSmartPhone());
            billPS.setDate(3, quanLyBH.getDate());
            billPS.setInt(4, quanLyBH.getQuantityPurchased());
            billPS.execute();
            ResultSet rs = billPS.getGeneratedKeys();
            int rowsAffected = 0;
            if(rs.next()){
                rowsAffected = rs.getInt(1);
                System.out.println("Đã chèn thành công. Khóa chính mới: " + rowsAffected);
                updateInventory(cons, rowsAffected);
            }
            billPS.close();
            cons.close();
            return rowsAffected;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteRecord(QuanLyBH quanLyBH) {
            try {
                Connection cons = DBConnect.getConnection();

                // Tìm tổng quantityPurchased theo idSmartPhone của hóa đơn
                String updateInventorySQL = "UPDATE ThongTinDT tt " +
                                            "JOIN (SELECT idSmartPhone, SUM(quantityPurchased) AS totalQuantity " +
                                                  "FROM Bill " +
                                                  "WHERE idBill = ? " +
                                                  "GROUP BY idSmartPhone) b " +
                                            "ON tt.IdSmartPhone = b.idSmartPhone " +
                                            "SET tt.quantityInventory = tt.quantityInventory + COALESCE(b.totalQuantity, 0)";
                PreparedStatement updateInventoryPS = cons.prepareStatement(updateInventorySQL);
                updateInventoryPS.setInt(1, quanLyBH.getIdBill());
                updateInventoryPS.executeUpdate();
                updateInventoryPS.close();

                // Xóa hóa đơn theo idBill
                String deleteBillSQL = "DELETE FROM Bill WHERE idBill = ?";
                PreparedStatement deleteBillPS = cons.prepareStatement(deleteBillSQL);
                deleteBillPS.setInt(1, quanLyBH.getIdBill());

                int rowsAffected = deleteBillPS.executeUpdate();

                deleteBillPS.close();
                cons.close();

                return rowsAffected;
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
    }
    
    
}
