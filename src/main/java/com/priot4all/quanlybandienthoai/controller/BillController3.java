/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.controller;

import com.priot4all.quanlybandienthoai.model.QuanLyBH;
import com.priot4all.quanlybandienthoai.service.QuanLyBHService;
import com.priot4all.quanlybandienthoai.service.QuanLyBHServiceImpl;
import com.priot4all.quanlybandienthoai.utility.ClassTableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author admin
 */
public class BillController3 {
    private JPanel jpnView;
    private JLabel jlbidBill;
    private JLabel jlbnameClient;
    private JLabel jlbnumberPhone;
    private JLabel jlbquantityPurchased;
    private JLabel jlbprice;
    private JLabel jlbtotalPrice;
    private JLabel jlbDate;
    
    private QuanLyBH quanLyBH = null;
    
    private QuanLyBHService quanLyBHService = null;
    
    private String[] listColumn = {"STT", "Tên điện thoại", "Hãng sản xuất", "Dung lượng", "Màu sắc"};
    
    private TableRowSorter<TableModel> rowSorter = null;

    public BillController3(JPanel jpnView,JLabel jlbidBill, JLabel jlbnameClient, JLabel jlbnumberPhone, JLabel jlbquantityPurchased, JLabel jlbprice, JLabel jlbtotalPrice, JLabel jlbDate) {
        this.jlbidBill = jlbidBill;
        this.jlbnameClient = jlbnameClient;
        this.jlbnumberPhone = jlbnumberPhone;
        this.jlbquantityPurchased = jlbquantityPurchased;
        this.jlbprice = jlbprice;
        this.jlbtotalPrice = jlbtotalPrice;
        this.jlbDate = jlbDate;
        this.jpnView = jpnView;
        
        this.quanLyBHService = new QuanLyBHServiceImpl();
    }
    
    public void setView(QuanLyBH quanLyBH){
        List<QuanLyBH> quanLyBHList = quanLyBHService.getAllLists();
         QuanLyBH selectedBill = findQuanLyBHByIdBill(quanLyBHList, quanLyBH.getIdBill());

    if (selectedBill != null) {
        // Hiển thị thông tin của bill được chọn
        jlbidBill.setText(Integer.toString(selectedBill.getIdBill()));
        jlbnameClient.setText(selectedBill.getNameClient());
        jlbnumberPhone.setText(selectedBill.getNumberPhoneClient());
        jlbquantityPurchased.setText(Integer.toString(selectedBill.getQuantityPurchased()));
        jlbprice.setText(Double.toString(selectedBill.getPrice()));
        jlbtotalPrice.setText(Double.toString(selectedBill.getTotalPrice()));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String dateString = dateFormat.format(selectedBill.getDate());
        jlbDate.setText(dateString);

        // Hiển thị thông tin của khách hàng
        QuanLyBH clientInfo = findQuanLyBHByClientId(quanLyBHList, selectedBill.getIdClient());
        if (clientInfo != null) {
            jlbnameClient.setText(clientInfo.getNameClient());
            jlbnumberPhone.setText(clientInfo.getNumberPhoneClient());
        }
    }
}
    private QuanLyBH findQuanLyBHByClientId(List<QuanLyBH> quanLyBHList, int idClient) {
    for (QuanLyBH client : quanLyBHList) {
        if (client.getIdClient() == idClient) {
            return client;
        }
    }
    return null;
}

private QuanLyBH findQuanLyBHByIdBill(List<QuanLyBH> quanLyBHList, int idBill) {
    for (QuanLyBH quanLyBH : quanLyBHList) {
        if (quanLyBH.getIdBill() == idBill) {
            return quanLyBH;
        }
    }
    return null;
}
    
    
    
    public void setDateToTable(QuanLyBH quanLyBH) {
        List<QuanLyBH> quanLyBHList = quanLyBHService.getAllLists();
        QuanLyBH selectedBill = findQuanLyBHByIdBill(quanLyBHList, quanLyBH.getIdBill());
        int desiredIdSmartPhone = selectedBill.getIdSmartPhone(); // Sửa thành getIdSmartPhone()
        List<QuanLyBH> filteredList = quanLyBHList.stream()
                .filter(item -> item.getIdSmartPhone() == desiredIdSmartPhone)
                .collect(Collectors.toList());
          System.out.println("=== Filtered List ===");
            for (QuanLyBH item : filteredList) {
                System.out.println(item); // Hoặc in ra các thuộc tính cụ thể của mỗi item
            }
          
         DefaultTableModel model = new ClassTableModel().setTableBillnew(filteredList, listColumn);
         JTable table = new JTable(model);
         
         
         rowSorter = new TableRowSorter<>(table.getModel());
         table.setRowSorter(rowSorter);
         
         table.getColumnModel().getColumn(1).setMinWidth(80);
         table.getColumnModel().getColumn(1).setMaxWidth(80);
         table.getColumnModel().getColumn(1).setPreferredWidth(80);
         
         table.getTableHeader().setFont(new Font("Time New Roman", Font.BOLD, 14));
         table.getTableHeader().setPreferredSize(new Dimension(100,50));
         table.setRowHeight(50);
         table.revalidate();
         table.repaint();
         
         JScrollPane scrollPane = new JScrollPane();
         scrollPane.getViewport().add(table);
         scrollPane.setPreferredSize(new Dimension(700,90));
         
         jpnView.removeAll();
         jpnView.setLayout(new BorderLayout());
         jpnView.add(scrollPane);
         jpnView.revalidate();
         jpnView.repaint();
    }
}
