/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.controller;

import com.priot4all.quanlybandienthoai.model.ThongTinDT;
import com.priot4all.quanlybandienthoai.service.ThongTinDTService;
import com.priot4all.quanlybandienthoai.service.ThongTinDTServiceImpl;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 *
 * @author admin
 */
public class DienThoaiController {
    
    private JButton btnSubmit;
    private JLabel jtfiIdSmartPhone;
    private JTextField jtfnameSmartPhone;
    private JSpinner jtfquantityInventory;
    private JSpinner jtfprice;
    private JTextField jtfcategory;
    private JSpinner jtfcapacity;
    private JTextField jtfcolor;
    
    private JLabel jlbCanhBao;
    
    private ThongTinDT thongTinDT = null;
    private ThongTinDTService thongTinDTService = null;

    public DienThoaiController(JButton btnSubmit, JLabel jtfiIdSmartPhone, JTextField jtfnameSmartPhone, JSpinner jtfquantityInventory, JSpinner jtfprice, JTextField jtfcategory, JSpinner jtfcapacity, JTextField jtfcolor, JLabel jlbCanhBao ) {
        this.btnSubmit = btnSubmit;
        this.jtfiIdSmartPhone = jtfiIdSmartPhone;
        this.jtfnameSmartPhone = jtfnameSmartPhone;
        this.jtfquantityInventory = jtfquantityInventory;
        this.jtfprice = jtfprice;
        this.jtfcategory = jtfcategory;
        this.jtfcapacity = jtfcapacity;
        this.jtfcolor = jtfcolor;
        this.jlbCanhBao = jlbCanhBao;
        
        this.thongTinDTService = new ThongTinDTServiceImpl();
    }

    
    
    public void setView(ThongTinDT thongTinDT){
        this.thongTinDT = thongTinDT;
        jtfiIdSmartPhone.setText(Integer.toString(thongTinDT.getIdSmartPhone()));
        jtfnameSmartPhone.setText(thongTinDT.getNameSmartPhone());
        jtfquantityInventory.setValue(thongTinDT.getQuantityInventory());
        jtfprice.setValue(thongTinDT.getPrice());
        jtfcategory.setText(thongTinDT.getCategory());
        jtfcapacity.setValue(thongTinDT.getCapacity());
        jtfcolor.setText(thongTinDT.getColor());
        //setEvent();
    }
    
    
    
    public void setEvent() {
        // cài đặt hiệu ứng cho nút button submit
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(jtfnameSmartPhone.getText().length() == 0 || jtfcolor.getText().length() == 0) {
                    jlbCanhBao.setText("Vui lòng nhập đầy đủ thông tin");
                } else {
                    // test thử với lấy khóa thủ công với getText
                 
                   
                    //thongTinDT.setIdSmartPhone(jtfiIdSmartPhone.getText());
                    thongTinDT.setNameSmartPhone(jtfnameSmartPhone.getText());
                    thongTinDT.setQuantityInventory((int) jtfquantityInventory.getValue());
                    thongTinDT.setPrice(((Number) jtfprice.getValue()).doubleValue());
                    thongTinDT.setCategory(jtfcategory.getText());
                    thongTinDT.setCapacity((int) jtfcapacity.getValue());
                    thongTinDT.setColor(jtfcolor.getText());
                    
                    
                    int lastId = thongTinDTService.createOrUpdate(thongTinDT);
                    if(lastId > 0) {
                        thongTinDT.setIdSmartPhone(lastId);
                        jtfiIdSmartPhone.setText(Integer.toString(lastId));
                        System.out.println("Lasid" + lastId);
                        jlbCanhBao.setText("Cập nhật thành công");
                    } else {
                        jlbCanhBao.setText("Cập nhật không thành công");
                    }
                }
            }       
        });
        
    }
    
    
}
