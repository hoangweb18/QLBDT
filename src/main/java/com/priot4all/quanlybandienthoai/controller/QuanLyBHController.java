/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.controller;

import com.priot4all.quanlybandienthoai.model.KhachHang;
import com.priot4all.quanlybandienthoai.model.QuanLyBH;
import com.priot4all.quanlybandienthoai.model.ThongTinDT;
import com.priot4all.quanlybandienthoai.service.QuanLyBHService;
import com.priot4all.quanlybandienthoai.service.QuanLyBHServiceImpl;
import com.priot4all.quanlybandienthoai.utility.ClassTableModel;
import com.priot4all.quanlybandienthoai.view.ThemSuaBHJFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author admin
 */
public class QuanLyBHController {
    
    
    private JPanel jpnView;
    private JButton btnAdd;
    private JTextField jtfSearch;
    private JButton btnDelete;
    private JButton btnRefresh;
    
    private QuanLyBHService quanLyBHService = null;
    
    private String[] listColumn = {"Mã đơn hàng", "Mã Khách Hàng", "STT", "Tên Điện Thoại", "Ngày", "Thành Tiền"};
    
    private TableRowSorter<TableModel> rowSorter = null;

    public QuanLyBHController(JButton btnRefresh,JPanel jpnView, JButton btnAdd, JTextField jtfSearch ) {
        this.btnRefresh = btnRefresh;
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.jtfSearch = jtfSearch;
        
        this.quanLyBHService = new QuanLyBHServiceImpl();
    }
    
    public void setDateToTable() {
        List<QuanLyBH> listItem = quanLyBHService.getList();
        
        DefaultTableModel model = new ClassTableModel().setTableBill(listItem, listColumn);
        JTable table = new JTable(model);
        
        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);
        
        // chức năng tìm kiếm dựa trên so sánh ký tự
        jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
             @Override
             public void insertUpdate(DocumentEvent e) {
                 String text = jtfSearch.getText();
                 if(text.trim().length() == 0){
                     rowSorter.setRowFilter(null);
                 } else {
                     rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                 }
             }

             @Override
             public void removeUpdate(DocumentEvent e) {
                 String text = jtfSearch.getText();
                 if(text.trim().length() == 0){
                     rowSorter.setRowFilter(null);
                 } else {
                     rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                 }
             }
              @Override
             public void changedUpdate(DocumentEvent e) {
             }
         });
        // chức năng thêm, sửa
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount() == 2 && table.getSelectedRow() != -1){
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();
                    
                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);
                    
                    QuanLyBH quanLyBH = new QuanLyBH();
                    List<QuanLyBH> listItem = quanLyBHService.getAllList();
                    
                    
                    
                    quanLyBH.setIdBill(Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString()));
                    quanLyBH.setIdClient(Integer.parseInt(model.getValueAt(selectedRowIndex, 1).toString()));
                    
                    quanLyBH.setNamesmartPhone(model.getValueAt(selectedRowIndex, 3).toString());
                    quanLyBH.setDate(model.getValueAt(selectedRowIndex, 4).toString());
                    quanLyBH.setTotalPrice(Double.parseDouble(model.getValueAt(selectedRowIndex, 5).toString()));
                    
                    
                    //int idClient = Integer.parseInt(model.getValueAt(selectedRowIndex, 2).toString());
                    //quanLyBH.setNameClient(quanLyBHService.getTenKhachHangByIdClient(idClient));
                    
                    
                    ThemSuaBHJFrame frame = new ThemSuaBHJFrame(quanLyBH);
                    frame.setTitle("Thông tin đơn hàng");
                    frame.setLocationRelativeTo(null);
                    frame.setResizable(false);
                    frame.setVisible(true);
                }
            }
        });
        
        
        
        
        table.getColumnModel().getColumn(1).setMinWidth(80);
        table.getColumnModel().getColumn(1).setMaxWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(80);
        
        table.getTableHeader().setFont(new Font("Time New Roman", Font.BOLD, 14));
         table.getTableHeader().setPreferredSize(new Dimension(100,50));
         table.setRowHeight(50);
         table.validate();
         table.repaint();
         
         JScrollPane scrollPane = new JScrollPane();
         scrollPane.getViewport().add(table);
         scrollPane.setPreferredSize(new Dimension(1300,400));
         
         jpnView.removeAll();
         jpnView.setLayout(new BorderLayout());
         jpnView.add(scrollPane);
         jpnView.validate();
         jpnView.repaint();
    }
    
    
    // các sự kiện trong jPanel
    public void setEvent() {
        btnRefresh.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Gọi hàm làm mới JTable
            setDateToTable();
        }
    });
       btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ThemSuaBHJFrame frame = new ThemSuaBHJFrame(new QuanLyBH());
                frame.setTitle("Thông tin đơn hàng");
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                frame.setVisible(true);
            }
       });
    }
}

