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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 *
 * @author admin
 */
public class BillController {
    private JButton btnSubmit;
    private JTextField jtfidBill;
    private JTextField jtfidClient;
    private JComboBox jcbIdSmartPhone;
    private JTextField jtfSmartPhone;
    private JSpinner jspTotalPrice;
    private JTextField jtfDate;
    private JTextField jtfnameClient;
    
    
    private QuanLyBH quanLyBH = null;
    
    private QuanLyBHService quanLyBHService = null;

    public BillController(JButton btnSubmit, JTextField jtfidBill, JTextField jtfidClient, JSpinner jspTotalPrice, JTextField jtfDate,JTextField jtfnameClient) {
        this.btnSubmit = btnSubmit;
        this.jtfidBill = jtfidBill;
        this.jtfidClient = jtfidClient;
        //this.jcbIdSmartPhone = jcbIdSmartPhone;
        //this.jtfSmartPhone = jtfSmartPhone;
        this.jspTotalPrice = jspTotalPrice;
        this.jtfDate = jtfDate;
        this.jtfnameClient = jtfnameClient;
        
        quanLyBHService = new QuanLyBHServiceImpl();
    }

 
    
    public void setView(QuanLyBH quanLyBH){
        this.quanLyBH = quanLyBH;
        jtfidBill.setText(Integer.toString(quanLyBH.getIdBill()));
        jtfidClient.setText(Integer.toString(quanLyBH.getIdClient()));
        jtfnameClient.setText(quanLyBH.getNameClient());
        jtfDate.setText(quanLyBH.getDate());
        jspTotalPrice.setValue(quanLyBH.getTotalPrice());
    }
}
