/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.controller;

import com.priot4all.quanlybandienthoai.model.QuanLyBH;
import com.priot4all.quanlybandienthoai.service.QuanLyBHService;
import com.priot4all.quanlybandienthoai.service.QuanLyBHServiceImpl;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import org.jfree.ui.Spinner;

/**
 *
 * @author admin
 */
public class BillController2 {
    private JButton btnSubmit;
    private JLabel jlbidBill;
    private JComboBox jcbidClient;
    private JComboBox jcbidSmartPhone;
    private JDateChooser jdcDate;
    private JSpinner jspQuantityPurchased;
    private JLabel jlbCanhBao;
    
    private QuanLyBH quanLyBH = null;
    
    private QuanLyBHService quanLyBHService = null;

    public BillController2(JButton btnSubmit, JLabel jlbidBill, JComboBox jcbidClient, JComboBox jcbidSmartPhone, JDateChooser jdcDate, JSpinner jspQuantityPurchased,JLabel jlbCanhBao) {
        this.btnSubmit = btnSubmit;
        this.jlbidBill = jlbidBill;
        this.jcbidClient = jcbidClient;
        this.jcbidSmartPhone = jcbidSmartPhone;
        this.jdcDate = jdcDate;
        this.jspQuantityPurchased = jspQuantityPurchased;
        this.jlbCanhBao = jlbCanhBao;
        
        this.quanLyBHService = new QuanLyBHServiceImpl();
        
        initializeComboBox();
        
        initializeComboBox1();
    }
    
    public void setView(QuanLyBH quanLyBH){
        this.quanLyBH = quanLyBH;
    }
    
    private void initializeComboBox() {
        List<Integer> idsSmartPhoneList = quanLyBHService.getIdsSmartPhoneList();
        DefaultComboBoxModel<Integer> idSmartPhoneModel = new DefaultComboBoxModel<>(idsSmartPhoneList.toArray(new Integer[0]));
        jcbidSmartPhone.setModel(idSmartPhoneModel);
    }
        
        private void initializeComboBox1() {
        List<Integer> idsClient = quanLyBHService.getIdClientList();
        DefaultComboBoxModel<Integer> idClientModel = new DefaultComboBoxModel<>(idsClient.toArray(new Integer[0]));
        jcbidClient.setModel(idClientModel);
    }
    
    public void setEvent() {
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
              if((int) jspQuantityPurchased.getValue() == 0){
                  jlbCanhBao.setText("Vui lòng nhập đầy đủ thông tin");
                  System.out.println(".nhập cho đủ vào");
              } else {
                  //quanLyBH.setIdBill(Integer.parseInt(jlbidBill.getText()));
                  quanLyBH.setIdClient((int) jcbidClient.getSelectedItem());
                  quanLyBH.setIdSmartPhone((int) jcbidSmartPhone.getSelectedItem());
                  quanLyBH.setDate(covertDateToDateSql(jdcDate.getDate()));
                  quanLyBH.setQuantityPurchased((int) jspQuantityPurchased.getValue());
                  
                  int rowsAffected = quanLyBHService.createBill(quanLyBH);

                    if (rowsAffected > 0) {
                        quanLyBH.setIdBill(rowsAffected);
                        jlbidBill.setText(Integer.toString(rowsAffected));
                        System.out.println("Lasid" + rowsAffected);
                        System.out.println("Lưu thành công.");
                        jlbCanhBao.setText("Cập nhật thành công");
                    } else {
                        System.out.println("Lưu không thành công.");
                        jlbCanhBao.setText("Cập nhật không thành công");
                    }
              }
            }
            
        });
    }
        public java.sql.Date covertDateToDateSql(Date d) {
        return new java.sql.Date(d.getTime());
    }
}
