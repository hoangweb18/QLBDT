/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.controller;

import com.priot4all.quanlybandienthoai.model.KhachHang;
import com.priot4all.quanlybandienthoai.service.KhachHangService;
import com.priot4all.quanlybandienthoai.service.KhachHangServiceImpl;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author admin
 */
public class HienKhachHangController {
    private JButton btnSubmit;
    private JTextField jtfidClient;
    private JTextField jtfnameClient;
    private JTextField jtfnumberPhone;
    
    private JLabel jlbCanhBao;
    
    private KhachHang khachHang = null;
    
    private KhachHangService khachHangService = null;

    public HienKhachHangController(JButton btnSubmit, JTextField jtfidClient, JTextField jtfnameClient, JTextField jtfnumberPhone, JLabel jlbCanhBao) {
        this.btnSubmit = btnSubmit;
        this.jtfidClient = jtfidClient;
        this.jtfnameClient = jtfnameClient;
        this.jtfnumberPhone = jtfnumberPhone;
        this.jlbCanhBao = jlbCanhBao;
        
        
        this.khachHangService = new KhachHangServiceImpl();
    }
    
    public void setView(KhachHang khachHang){
        this.khachHang = khachHang;
        jtfidClient.setText(Integer.toString(khachHang.getIdClient()));
        jtfnameClient.setText(khachHang.getNameClient());
        jtfnumberPhone.setText(khachHang.getNumberPhone());
        System.out.println("ID: " + khachHang.getIdClient());
        System.out.println("Name: " + khachHang.getNameClient());
        System.out.println("Number Phone: " + khachHang.getNumberPhone());
    }
    
    public void setEvent() {
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(jtfnameClient.getText().length() == 0){
                    jlbCanhBao.setText("Nhập cho đủ thông tin vào");
                    System.out.println(".nhập cho đủ vào");
                } else {
                    khachHang.setIdClient(Integer.parseInt(jtfidClient.getText()));
                    khachHang.setNameClient(jtfnameClient.getText());
                    khachHang.setNumberPhone(jtfnumberPhone.getText());
                    System.out.println(".mouseClicked()");
                    
                    int lastId = khachHangService.createOrUpdate(khachHang);
                    if(lastId > 0) {
                        khachHang.setIdClient(lastId);
                        jtfidClient.setText(Integer.toString(lastId));
                        jlbCanhBao.setText("Cập nhật thành công");
                    } else {
                        JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật thông tin khách hàng. Có thể do trùng id hoặc có lỗi khác.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            
        });
    }
}
