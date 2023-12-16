/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.utility;

import com.priot4all.quanlybandienthoai.model.KhachHang;
import com.priot4all.quanlybandienthoai.model.QuanLyBH;
import com.priot4all.quanlybandienthoai.model.SmartPhone;
import com.priot4all.quanlybandienthoai.model.ThongTinDT;
import java.util.List;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class ClassTableModel {
    
    public DefaultTableModel setTableThongTinDT( List<ThongTinDT> listItem, String[] listColumn){
        DefaultTableModel dtm = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
            
        };
        dtm.setColumnIdentifiers(listColumn);
        int columns = listColumn.length;
        Object[] obj = null;
        int rows = listItem.size();
        if(rows > 0) {
            for(int i = 0; i < rows ;i++){
                ThongTinDT thongTinDT = listItem.get(i);
                obj = new Object[columns];
                obj[0] = thongTinDT.getIdSmartPhone();
                obj[1] = (i + 1);
                obj[2] = thongTinDT.getNameSmartPhone();
                obj[3] = thongTinDT.getQuantityInventory();
                obj[4] = thongTinDT.getPrice();
                obj[5] = thongTinDT.getCategory();
                obj[6] = thongTinDT.getCapacity();
                obj[7] = thongTinDT.getColor();
                dtm.addRow(obj);
            }
        }
        return dtm;
    }
    
    public DefaultTableModel setTableBill(List<QuanLyBH> listItem, String[] listColumn) {
    DefaultTableModel dtm = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    dtm.setColumnIdentifiers(listColumn);
    int columns = listColumn.length;
    Object[] obj = null;
    int rows = listItem.size();
    if (rows > 0) {
        for (int i = 0; i < rows; i++) {
            QuanLyBH bill = listItem.get(i);
            obj = new Object[columns];
            obj[0] = bill.getIdBill();
            obj[1] = bill.getIdClient();
            obj[2] = (i + 1);// số thứ tự
            obj[3] = bill.getNamesmartPhone();
            obj[4] = bill.getDate();
            obj[5] = bill.getTotalPrice();
            dtm.addRow(obj);
        }
    }
    return dtm;
}
    
    public DefaultTableModel setTableBillnew(List<QuanLyBH> listItem, String[] listColumn) {
    DefaultTableModel dtm = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    dtm.setColumnIdentifiers(listColumn);
    int columns = listColumn.length;
    Object[] obj = null;
    int rows = listItem.size();
    if (rows > 0) {
        for (int i = 0; i < rows; i++) {
            QuanLyBH bill = listItem.get(i);
            obj = new Object[columns];
            obj[0] = bill.getIdBill();
            obj[1] = bill.getIdClient();
            obj[2] = i + 1; // Số thứ tự hàng (có thể xóa nếu không cần)
            obj[3] = bill.getNameClient(); // Thêm tên khách hàng
            obj[4] = bill.getNumberPhoneClient(); // Thêm số điện thoại khách hàng
            obj[5] = bill.getNamesmartPhone();// Thêm tên smartphone
            obj[6] = bill.getQuantityPurchased(); // Thêm số lượng mua
            obj[7] = bill.getCapacity(); // Thêm dung lượng
            obj[8] = bill.getColor(); // Thêm màu sắc
            obj[9] = bill.getDate();
            obj[10] = bill.getPrice(); // Thêm giá
            obj[11] = bill.getTotalPrice();
            dtm.addRow(obj);
        }
    }
    return dtm;
}
    
    
    public DefaultTableModel setTableCustomer(List<KhachHang> listItem, String[] listColumn) {
    DefaultTableModel dtm = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    dtm.setColumnIdentifiers(listColumn);
    int columns = listColumn.length;
    Object[] obj = null;
    int rows = listItem.size();
    if (rows > 0) {
        for (int i = 0; i < rows; i++) {
            KhachHang customer = listItem.get(i);
            obj = new Object[columns];
            obj[0] = customer.getIdClient();// IdClient thay vì IdBill
            obj[1] = customer.getNameClient(); // NameClient thay vì IdClient
            obj[2] = customer.getNumberPhone();
            obj[3] = customer.getPurchaseStatus(); // Thêm PurchaseStatus
            dtm.addRow(obj);
        }
    }
    return dtm;
}
    
    
}
