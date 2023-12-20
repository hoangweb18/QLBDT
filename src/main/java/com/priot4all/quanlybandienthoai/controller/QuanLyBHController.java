/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.controller;

import com.itextpdf.awt.PdfGraphics2D;
import com.itextpdf.awt.geom.Rectangle2D;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.priot4all.quanlybandienthoai.model.KhachHang;
import com.priot4all.quanlybandienthoai.model.QuanLyBH;
import com.priot4all.quanlybandienthoai.model.ThongTinDT;
import com.priot4all.quanlybandienthoai.service.QuanLyBHService;
import com.priot4all.quanlybandienthoai.service.QuanLyBHServiceImpl;
import com.priot4all.quanlybandienthoai.service.ThongTinDTService;
import com.priot4all.quanlybandienthoai.service.ThongTinDTServiceImpl;
import com.priot4all.quanlybandienthoai.utility.ClassTableModel;
import com.priot4all.quanlybandienthoai.view.ThemSuaBHJFrame;
import com.priot4all.quanlybandienthoai.view.ThemSuaBHJFrame1;
import com.priot4all.quanlybandienthoai.view.ThemSuaBHJFrame2;
import com.priot4all.quanlybandienthoai.view.XuatHoaDonJFrame;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author admin
 */
public class QuanLyBHController {
    
    
    private JPanel jpnView;
    private JButton btnAdd;
    private JTextField jtfSearch;
    private JButton btnDelete;
    private JButton btnxuatDon;
    private JButton btnAdd1;
    private JButton btnXexcel;
    
    private QuanLyBHService quanLyBHService = null;
    
    private String[] listColumn = {"Mã đơn hàng", "Mã Khách Hàng", "Mã điện thoại","STT", "Ngày mua", "Số lượng mua"};
    
    private TableRowSorter<TableModel> rowSorter = null;

    public QuanLyBHController(JButton btnxuatDon,JPanel jpnView, JButton btnAdd, JTextField jtfSearch,JButton btnAdd1, JButton btnDelete, JButton btnXexcel ) {
        this.btnxuatDon = btnxuatDon;
        this.jpnView = jpnView;
        this.btnAdd = btnAdd;
        this.jtfSearch = jtfSearch;
        this.btnAdd1 = btnAdd1;
        this.btnDelete = btnDelete;
        this.btnXexcel = btnXexcel;
        
        this.quanLyBHService = new QuanLyBHServiceImpl();
    }
    
    public void setDateToTable() {
        List<QuanLyBH> listItem = quanLyBHService.getAllLists();
        
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
                    List<QuanLyBH> quanLyBHList  = quanLyBHService.getAllLists();
                    
                    
                    quanLyBH.setIdBill(Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString()));
                    quanLyBH.setIdClient(Integer.parseInt(model.getValueAt(selectedRowIndex, 1).toString()));
                    quanLyBH.setIdSmartPhone(Integer.parseInt(model.getValueAt(selectedRowIndex, 2).toString()));
               
                    quanLyBH.setDate((Date)model.getValueAt(selectedRowIndex, 4));
                    quanLyBH.setQuantityPurchased(Integer.parseInt(model.getValueAt(selectedRowIndex, 5).toString()));
                    
                    
                    
                    ThemSuaBHJFrame2 frame = new ThemSuaBHJFrame2(quanLyBH);
                    frame.setTitle("Thông tin đơn hàng");
                    frame.setLocationRelativeTo(null);
                    frame.setResizable(false);
                    frame.setVisible(true);
                }
                
                if(e.getClickCount() == 1 && table.getSelectedRow() != -1){
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    int selectedRowIndex = table.getSelectedRow();
                    //QuanLyBH tquan = new ThongTinDT();
                    selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);
                    //thongTinDT.setIdSmartPhone((int) model.getValueAt(selectedRowIndex, 0));
                    
                    btnDelete.setEnabled(true);

                }
                if(e.getClickCount() == 1 && table.getSelectedRow() != -1){
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    QuanLyBH quanLyBH = new QuanLyBH();
                    int selectedRowIndex = table.getSelectedRow();
                    quanLyBH.setIdBill(Integer.parseInt(model.getValueAt(selectedRowIndex, 0).toString()));
                    quanLyBH.setIdClient(Integer.parseInt(model.getValueAt(selectedRowIndex, 1).toString()));
                    quanLyBH.setIdSmartPhone(Integer.parseInt(model.getValueAt(selectedRowIndex, 2).toString()));
                    btnxuatDon.setEnabled(true);

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
                                QuanLyBH quanLyBH = new QuanLyBH();
                                selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);
                                quanLyBH.setIdBill((int) model.getValueAt(selectedRowIndex, 0));
                           
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
                                    if(quanLyBHService.deleteRecord(quanLyBH) > 0){
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
                               System.out.println("IdBill không được chọn!"); 
                               JOptionPane.showMessageDialog(
                                null,
                                "Vui lòng chọn một Mã đơn hàng trên bảng để thực hiện hành động!",
                                "Cảnh báo",
                                JOptionPane.WARNING_MESSAGE
                            );
                    }
                }
            });
        
        btnxuatDon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button clicked!");
                            
                            if(table.getSelectedRow() != -1){
                                DefaultTableModel model = (DefaultTableModel) table.getModel();
                                int selectedRowIndex = table.getSelectedRow();
                                QuanLyBH quanLyBH = new QuanLyBH();
                                selectedRowIndex = table.convertRowIndexToModel(selectedRowIndex);
                                quanLyBH.setIdBill((int) model.getValueAt(selectedRowIndex, 0));
                           
                            // Hiển thị hộp thoại xác nhận trước khi xóa
                            int confirmChoice;
                                confirmChoice = JOptionPane.showConfirmDialog(
                                null,
                                "Bạn có chắc chắn muốn xuất đơn hàng này",
                                "Xác nhận xuất",
                                JOptionPane.YES_NO_OPTION
                                );
                                // Nếu người dùng chọn Yes, thì gọi hàm xóa
                                if (confirmChoice == JOptionPane.YES_OPTION) {
                                        XuatHoaDonJFrame frame = new XuatHoaDonJFrame(quanLyBH);
                                            frame.setTitle("Thông tin đơn hàng");
                                            frame.setLocationRelativeTo(null);
                                            frame.setResizable(false);
                                            frame.setVisible(true);
                                            frame.exportToPDF();
                                        JOptionPane.showMessageDialog(
                                            null, 
                                            "Đã tạo thành công", 
                                            "Thông báo", 
                                            JOptionPane.OK_CANCEL_OPTION);
                                            
                                }
                    } else {
                               System.out.println("idBill không được chọn!"); 
                               JOptionPane.showMessageDialog(
                                null,
                                "Vui lòng chọn một Mã đơn hàng trên bảng để thực hiện hành động!",
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
    
    
    // các sự kiện trong jPanel
    public void setEvent() {
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
        btnAdd1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ThemSuaBHJFrame1 frame = new ThemSuaBHJFrame1(new QuanLyBH());
                frame.setTitle("Thông tin đơn hàng");
                frame.setLocationRelativeTo(null);
                frame.setResizable(false);
                frame.setVisible(true);
            }
       });
        btnXexcel.addActionListener(new ActionListener() {
           @Override
           public void actionPerformed(ActionEvent e) {
               try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet spreadsheet = workbook.createSheet("Đơn hàng");
            
            XSSFRow row = null;
            Cell cell = null;
            
            row = spreadsheet.createRow((short) 2);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Danh Sách đơn hàng");
            
            row = spreadsheet.createRow((short) 3);
            row.setHeight((short) 500);
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Mã đơn hàng");
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("Mã khách hàng");
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue("Mã điện thoại");
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue("STT");
            cell = row.createCell(4, CellType.STRING);
            cell.setCellValue("Tên khách hàng");
            cell = row.createCell(5, CellType.STRING);
            cell.setCellValue("Số điện thoại");
            cell = row.createCell(6, CellType.STRING);
            cell.setCellValue("Tên điện thoại");
            cell = row.createCell(7, CellType.STRING);
            cell.setCellValue("số lượng mua");
            cell = row.createCell(8,CellType.STRING);
            cell.setCellValue("Hãng sản xuất");
            cell = row.createCell(9,CellType.STRING);
            cell.setCellValue("Màu sắc");
            cell = row.createCell(10,CellType.STRING);
            cell.setCellValue("Ngày mua");
            cell = row.createCell(11,CellType.STRING);
            cell.setCellValue("Đơn giá");
            cell = row.createCell(12,CellType.STRING);
            cell.setCellValue("Dung lượng");
            cell = row.createCell(13,CellType.STRING);
            cell.setCellValue("Thành tiền");
            
            QuanLyBHService quanLyBHService = new QuanLyBHServiceImpl();
            
            List<QuanLyBH> listItem = quanLyBHService.getAllLists();
            
            CreationHelper creationHelper = workbook.getCreationHelper();
            CellStyle dateCellStyle = workbook.createCellStyle();
            dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd"));
            
            for (int i = 0; i < listItem.size(); i++) {
            QuanLyBH quanLyBH = listItem.get(i);
            row = spreadsheet.createRow((short) 4 + i);
            row.setHeight((short) 400);
            row.createCell(0).setCellValue(quanLyBH.getIdBill());
            row.createCell(1).setCellValue(quanLyBH.getIdClient());
            row.createCell(2).setCellValue(quanLyBH.getIdSmartPhone());
            row.createCell(3).setCellValue(i + 1);
            row.createCell(4).setCellValue(quanLyBH.getNameClient());
            row.createCell(5).setCellValue(quanLyBH.getNumberPhoneClient());
            row.createCell(6).setCellValue(quanLyBH.getNamesmartPhone());
            row.createCell(7).setCellValue(quanLyBH.getQuantityPurchased());
            row.createCell(8).setCellValue(quanLyBH.getCategory());
            row.createCell(9).setCellValue(quanLyBH.getColor());
            Cell dateCell = row.createCell(10);
                dateCell.setCellValue(quanLyBH.getDate());
                dateCell.setCellStyle(dateCellStyle);
                spreadsheet.autoSizeColumn(10);
            row.createCell(11).setCellValue(quanLyBH.getPrice());
            row.createCell(12).setCellValue(quanLyBH.getCapacity());
            row.createCell(13).setCellValue(quanLyBH.getTotalPrice());
            }
            
            FileOutputStream out = new FileOutputStream(new File("D:/ĐơnHàng.xlsx"));
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

