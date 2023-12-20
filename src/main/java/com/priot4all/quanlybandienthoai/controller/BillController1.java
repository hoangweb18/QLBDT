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

/**
 *
 * @author admin
 */
public class BillController1 {
    private JButton btnSubmit;
    private JLabel jlbidBill;
    private JComboBox jcbidClient;
    private JComboBox jcbIdsmartPhone;
    private JLabel jlbnameClient;
    private JLabel jlbnumberPhone;
    private JDateChooser jdcDate;
    private JSpinner jspQuantityPurchased;
    private JLabel jlbThongBao;
    
    private QuanLyBH quanLyBH = null;
    
    private QuanLyBHService quanLyBHService = null;

    public BillController1(JLabel jlbidBill, JComboBox jcbidClient, JComboBox jcbIdsmartPhone, JLabel jlbnameClient, JLabel jlbnumberPhone, JDateChooser jdcDate, JSpinner jspQuantityPurchased, JButton btnSubmit, JLabel jlbThongBao) {
        this.jlbidBill = jlbidBill;
        this.jcbidClient = jcbidClient;
        this.jcbIdsmartPhone = jcbIdsmartPhone;
        this.jlbnameClient = jlbnameClient;
        this.jlbnumberPhone = jlbnumberPhone;
        this.jdcDate = jdcDate;
        this.jspQuantityPurchased = jspQuantityPurchased;
        this.btnSubmit = btnSubmit;
        this.jlbThongBao = jlbThongBao;
        
        this.quanLyBHService = new QuanLyBHServiceImpl();
        
        
        initializeComboBox();
        
        initializeComboBox1();
    }
    
    public void setView(QuanLyBH quanLyBH) {
        List<QuanLyBH> quanLyBHList = quanLyBHService.getAllLists();
        this.quanLyBH = quanLyBH;
        jlbidBill.setText(Integer.toString(quanLyBH.getIdBill()));
        
        jcbidClient.addItem(quanLyBH.getIdClient());
        jcbidClient.setSelectedItem(quanLyBH.getIdClient());
        
        jcbIdsmartPhone.addItem(quanLyBH.getIdSmartPhone());
        jcbIdsmartPhone.setSelectedItem(quanLyBH.getIdSmartPhone()); ;
        jlbnameClient.setText(quanLyBH.getNameClient());
        jlbnumberPhone.setText(quanLyBH.getNumberPhoneClient());
        jdcDate.setDate(quanLyBH.getDate());
        jspQuantityPurchased.setValue(quanLyBH.getQuantityPurchased());
        
        QuanLyBH clientInfo = findQuanLyBHByClientId(quanLyBHList, quanLyBH.getIdClient());
        if (clientInfo != null) {
                jlbnameClient.setText(clientInfo.getNameClient());
                jlbnumberPhone.setText(clientInfo.getNumberPhoneClient());
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
        private void initializeComboBox() {
        List<Integer> idsSmartPhoneList = quanLyBHService.getIdsSmartPhoneList();
        DefaultComboBoxModel<Integer> idSmartPhoneModel = new DefaultComboBoxModel<>(idsSmartPhoneList.toArray(new Integer[0]));
        jcbIdsmartPhone.setModel(idSmartPhoneModel);
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
                  jlbThongBao.setText("Vui lòng điền đủ thông tin");
                  System.out.println(".nhập cho đủ vào");
              } else {
                  //quanLyBH.setIdBill(Integer.parseInt(jlbidBill.getText()));
                  quanLyBH.setIdClient((int) jcbidClient.getSelectedItem());
                  quanLyBH.setIdSmartPhone((int) jcbIdsmartPhone.getSelectedItem());
                  //quanLyBH.setNameClient(jlbnumberPhone.getText());
                  //quanLyBH.setNumberPhoneClient(jlbnumberPhone.getText());
                  quanLyBH.setDate(covertDateToDateSql(jdcDate.getDate()));
                  quanLyBH.setQuantityPurchased((int) jspQuantityPurchased.getValue());
                  
                  int rowsAffected = quanLyBHService.updateBill(quanLyBH);

                    if (rowsAffected > 0) {
                        quanLyBH.setIdBill(rowsAffected);
                        jlbidBill.setText(Integer.toString(rowsAffected));
                        jlbThongBao.setText("Cập nhật thành công");
                        System.out.println("Lasid" + rowsAffected);
                        System.out.println("Lưu thành công.");
                    } else {
                        System.out.println("Lưu không thành công.");
                    }
              }
            }
            
        });
    }
        public java.sql.Date covertDateToDateSql(Date d) {
        return new java.sql.Date(d.getTime());
    }
}
