/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.controller;

import com.priot4all.quanlybandienthoai.dao.ThongTinDTDAO;
import com.priot4all.quanlybandienthoai.dao.ThongTinDTDAOImpl;
import com.priot4all.quanlybandienthoai.model.ThongTinDT;
import com.priot4all.quanlybandienthoai.service.ThongTinDTService;
import com.priot4all.quanlybandienthoai.service.ThongTinDTServiceImpl;
import com.priot4all.quanlybandienthoai.utility.ClassTableModel;
import com.priot4all.quanlybandienthoai.view.MainJFrame;
import com.priot4all.quanlybandienthoai.view.SuaThemDTJFrame;
import com.priot4all.quanlybandienthoai.view.ThongTinDTJPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.security.auth.Refreshable;
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
public class QuanLySanPhamController {
    private JPanel jpnView;
    private JButton btnAdd;
    private JTextField jtfSearch;
    private JButton btnDelete;
    private JButton btnPrint;
    
    
    private ThongTinDTService thongTinDTService = null; 
    
    private String[] listColumn = {"Mã điện thoại", "STT", "Tên điện thoại", "Số lượng", "Đơn giá", "Hãng sản xuất", "Dung lượng", "Màu sắc"};
    
    private TableRowSorter<TableModel> rowSorter = null;

    public QuanLySanPhamController(JPanel jpnView, JButton btnAdd, JTextField jtfSearch, JButton btnDelete,JButton btnPrint) {
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.jtfSearch = jtfSearch;
        this.btnDelete = btnDelete;
        this.btnPrint = btnPrint;
        
        this.thongTinDTService = new ThongTinDTServiceImpl();
    }
    
     public void setDateToTable() {
         List<ThongTinDT> listItem = thongTinDTService.getList();
         
         DefaultTableModel model = new ClassTableModel().setTableThongTinDT(listItem, listColumn);
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
         
         table.getColumnModel().getColumn(1).setMinWidth(80);
         table.getColumnModel().getColumn(1).setMaxWidth(80);
         table.getColumnModel().getColumn(1).setPreferredWidth(80);
         
     /*    table.getColumnModel().getColumn(0).setMinWidth(0);
         table.getColumnModel().getColumn(0).setMaxWidth(0);
         table.getColumnModel().getColumn(0).setPreferredWidth(0); */
     
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e){
                if(e.getClickCount() == 2 && table.getSelectedRow() != -1){
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();
                    
                    
                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);

                    //System.out.println(selectedRowIndex);
                    
                    ThongTinDT thongTinDT = new ThongTinDT();
                    
                    
                    thongTinDT.setIdSmartPhone((int) model.getValueAt(selectedRowIndex, 0));
                    thongTinDT.setNameSmartPhone(model.getValueAt(selectedRowIndex, 2).toString());
                    thongTinDT.setQuantityInventory((int) model.getValueAt(selectedRowIndex, 3));
                    thongTinDT.setPrice((double) model.getValueAt(selectedRowIndex,4));
                    thongTinDT.setCategory(model.getValueAt(selectedRowIndex, 5).toString());
                    thongTinDT.setCapacity((int) model.getValueAt(selectedRowIndex, 6));
                    thongTinDT.setColor(model.getValueAt(selectedRowIndex, 7).toString());
                    
                    SuaThemDTJFrame frame = new SuaThemDTJFrame(thongTinDT);
                    frame.setTitle("Thêm thông tin điện thoại");
                    frame.setResizable(false);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    
                }
                
                if(e.getClickCount() == 1 && table.getSelectedRow() != -1){
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                int selectedRowIndex = table.getSelectedRow();
                ThongTinDT thongTinDT = new ThongTinDT();
                selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);
                thongTinDT.setIdSmartPhone((int) model.getValueAt(selectedRowIndex, 0));
                
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
                                ThongTinDT thongTinDT = new ThongTinDT();
                                selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);
                                thongTinDT.setIdSmartPhone((int) model.getValueAt(selectedRowIndex, 0));
                           
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
                                    if(thongTinDTService.deleteRecord(thongTinDT) > 0){
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
                               System.out.println("IdSmartPhone không được chọn!"); 
                               JOptionPane.showMessageDialog(
                                null,
                                "Vui lòng chọn một IdSmartPhone trên bảng để thực hiện hành động!",
                                "Cảnh báo",
                                JOptionPane.WARNING_MESSAGE
                            );
                    }
                }
            });
        
        
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
     
     /*public void deleteRecord(ThongTinDT thongTinDT) {
     // Gọi hàm deleteRecord từ ThongTinDTDAOImpl
     ThongTinDTDAOImpl daoImpl = new ThongTinDTDAOImpl();
     daoImpl.deleteRecord(thongTinDT);
     }*/
     
     public void setEvent(){
         btnAdd.addMouseListener(new MouseAdapter() {
             @Override
             public void mouseClicked(MouseEvent e){
                 SuaThemDTJFrame frame = new SuaThemDTJFrame(new ThongTinDT());
                 frame.setTitle("Thông tin điện thoại");
                 frame.setLocationRelativeTo(null);
                 frame.setResizable(false);
                 frame.setVisible(true);
                 
             }
         });
         
         
         
        btnPrint.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Gọi hàm làm mới Print
            try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("Điện thoại");
            
            XSSFRow row = null;
            Cell cell = null;
            
            row = spreadsheet.createRow((short) 2);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Danh Sách Điện Thoại");
            
            row = spreadsheet.createRow((short) 3);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Mã điện thoại");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("STT");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Tên điện thoại");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("Số lượng");
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Đơn giá");
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Hãng sản xuất");
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Dung lượng");
            cell = row.createCell(7,CellType.STRING);
            cell.setCellValue("Màu sắc");
            
            ThongTinDTService thongTinDTService = new ThongTinDTServiceImpl();
            
            List<ThongTinDT> listItem = thongTinDTService.getList();
            
            for (int i = 0; i < listItem.size(); i++) {
            ThongTinDT thongTinDT = listItem.get(i);
            row = spreadsheet.createRow((short) 4 + i);
            row.setHeight((short) 400);
            row.createCell(0).setCellValue(thongTinDT.getIdSmartPhone());
            row.createCell(1).setCellValue(i + 1);
            row.createCell(2).setCellValue(thongTinDT.getNameSmartPhone());
            row.createCell(3).setCellValue(thongTinDT.getQuantityInventory());
            row.createCell(4).setCellValue(thongTinDT.getPrice());
            row.createCell(5).setCellValue(thongTinDT.getCategory());
            row.createCell(6).setCellValue(thongTinDT.getCapacity());
            row.createCell(7).setCellValue(thongTinDT.getColor());
            }
            
            FileOutputStream out = new FileOutputStream(new File("D:/ThongTinDT.xlsx"));
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
