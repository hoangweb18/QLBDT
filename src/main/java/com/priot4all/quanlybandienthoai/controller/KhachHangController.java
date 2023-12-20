/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.controller;

import com.priot4all.quanlybandienthoai.model.KhachHang;
import com.priot4all.quanlybandienthoai.service.KhachHangService;
import com.priot4all.quanlybandienthoai.service.KhachHangServiceImpl;
import com.priot4all.quanlybandienthoai.utility.ClassTableModel;
import com.priot4all.quanlybandienthoai.view.SuaThemKHJFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
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
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author admin
 */
public class KhachHangController {
    
    private JPanel jpnView;
    private JButton btnAdd;
    private JTextField jtfSearch;
    private JButton btnDelete;
    private JButton btnPrint;
    
    private KhachHangService khachHangService = null;
    
    private String[] listColumn = {"Mã khách hàng", "Tên khách hàng", "Số điện thoại", "Trạng thái"};
    
    private TableRowSorter<TableModel> rowSorter = null;

    public KhachHangController(JPanel jpnView, JButton btnAdd, JTextField jtfSearch, JButton btnDelete,JButton btnPrint) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.jtfSearch = jtfSearch;
        this.btnDelete = btnDelete;
        this.btnPrint = btnPrint;
        
        this.khachHangService = new KhachHangServiceImpl();
    }
 
    public void setDateToTable() {
        List<KhachHang> listItem = khachHangService.getList();
        
        DefaultTableModel model = new ClassTableModel().setTableCustomer(listItem, listColumn);
        JTable table = new JTable(model);
        
        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);
        
        // chức năng tìm kiếm trong bảng
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
         
         
         table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                if(e.getClickCount() == 2 && table.getSelectedRow() != -1){
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();
                    
                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);
                    KhachHang khachHang = new KhachHang();
                    khachHang.setIdClient(Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString()));
                    khachHang.setNameClient(model.getValueAt(selectedRowIndex, 1).toString());
                    khachHang.setNumberPhone(model.getValueAt(selectedRowIndex,2).toString());
                    
                            
                    SuaThemKHJFrame frame = new SuaThemKHJFrame(khachHang);
                    frame.setTitle("Thêm thông tin khách hàng");
                    frame.setResizable(false);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
         
                }
            
                if(e.getClickCount() == 1 && table.getSelectedRow() != -1){
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();
                    KhachHang khachHang = new KhachHang();
                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);
                    khachHang.setIdClient(Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString()));
                    btnDelete.setEnabled(true);
                
                }
            }
        });
         
         
            btnDelete.addActionListener(new ActionListener() {
                           @Override
                           public void actionPerformed(ActionEvent e) {
                               System.out.println("Button clicked!");

                               if(table.getSelectedRow() != -1){
                                   DefaultTableModel model = (DefaultTableModel) table.getModel();
                                   int selectedRowIndex = table.getSelectedRow();
                                   KhachHang khachHang = new KhachHang();
                                   selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);
                                   khachHang.setIdClient(Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString()));

                               // Hiển thị hộp thoại xác nhận trước khi xóa
                               int confirmChoice;
                                   confirmChoice = JOptionPane.showConfirmDialog(
                                   null,
                                   "Bạn có chắc muốn xóa bản ghi này?",
                                   "Xác nhận xóa",
                                   JOptionPane.YES_NO_OPTION
                                   );
                                   // Nếu người dùng chọn Yes, thì gọi hàm xóa
                                   if (confirmChoice == JOptionPane.YES_OPTION) {
                                       if(khachHangService.deleteRecord(khachHang) > 0){
                                           JOptionPane.showMessageDialog(
                                               null, 
                                               "Đã xóa thành công", 
                                               "Thông báo", 
                                               JOptionPane.OK_CANCEL_OPTION);
                                       } else {
                                           JOptionPane.showMessageDialog(null, "Không thể xóa dòng. Có thể do ràng buộc khóa ngoại. Vui lòng xóa đơn hàng trước", "Lỗi", JOptionPane.ERROR_MESSAGE);
                                       }
                                       } else {
                                       System.out.println("Người dùng đã hủy xóa.");
                                   }
                       } else {
                                  System.out.println("IdClient không được chọn!"); 
                                  JOptionPane.showMessageDialog(
                                   null,
                                   "Vui lòng chọn một idClient trên bảng để thực hiện hành động!",
                                   "Cảnh báo",
                                   JOptionPane.WARNING_MESSAGE
                               );
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
    
    
    public void setEvent() {
        btnAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SuaThemKHJFrame frame = new SuaThemKHJFrame(new KhachHang());
                 frame.setTitle("Thông tin khách hàng");
                 frame.setLocationRelativeTo(null);
                 frame.setResizable(false);
                 frame.setVisible(true);
            }
        });
        btnPrint.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("Khách Hàng");
            
            XSSFRow row = null;
            Cell cell = null;
            
            row = spreadsheet.createRow((short) 2);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Danh Sách khách hàng");
            
            row = spreadsheet.createRow((short) 3);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Mã khách hàng");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("STT");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tên khách hàng");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Số điện thoại");
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Trạng thái");
            
            KhachHangService khachHangService = new KhachHangServiceImpl();
            
            List<KhachHang> listItem = khachHangService.getList();
            
            for (int i = 0; i < listItem.size(); i++) {
            KhachHang khachHang = listItem.get(i);
            row = spreadsheet.createRow((short) 4 + i);
            row.setHeight((short) 400);
            row.createCell(0).setCellValue(khachHang.getIdClient());
            row.createCell(1).setCellValue(i + 1);
            row.createCell(2).setCellValue(khachHang.getNameClient());
            row.createCell(3).setCellValue(khachHang.getNumberPhone());
            row.createCell(4).setCellValue(khachHang.getPurchaseStatus());
            }
            
            FileOutputStream out = new FileOutputStream(new File("D:/KhachHang.xlsx"));
            workbook.write(out);
            out.close();
            JOptionPane.showMessageDialog(null, "Đã Xuất file thành công", "Thông báo",JOptionPane.OK_CANCEL_OPTION );
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            }
        });
    }
}
