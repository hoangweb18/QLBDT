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
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

/**
 *
 * @author admin
 */
public class BillController {
    private JButton btnSubmit;
    private JLabel jlbBill;
    private JTextField jtfidClient;
    private JComboBox jcbIdSmartPhone;
    private JDateChooser jdcDate;
    private JSpinner jspQuantityPurchased;
    private JTextField jtfNameClient;
    private JTextField jtfPnumberphoneClient;
    private JLabel jlbCanhBao;
    
    
    private QuanLyBH quanLyBH = null;
    
    private QuanLyBHService quanLyBHService = null;

    public BillController(JButton btnSubmit, JLabel jlbBill, JTextField jtfidClient, JComboBox jcbIdSmartPhone, JDateChooser jdcDate, JSpinner jspQuantityPurchased, JTextField jtfNameClient, JTextField jtfPnumberphoneClient, JLabel jlbCanhBao) {
        this.btnSubmit = btnSubmit;
        this.jlbBill = jlbBill;
        this.jtfidClient = jtfidClient;
        this.jcbIdSmartPhone = jcbIdSmartPhone;
        this.jdcDate = jdcDate;
        this.jspQuantityPurchased = jspQuantityPurchased;
        this.jtfNameClient = jtfNameClient;
        this.jtfPnumberphoneClient = jtfPnumberphoneClient;
        this.jlbCanhBao = jlbCanhBao;
        
        this.quanLyBHService = new QuanLyBHServiceImpl();
        
        initializeComboBox();
    }

    public void setView(QuanLyBH quanLyBH){
        List<QuanLyBH> quanLyBHList = quanLyBHService.getAllLists();
        this.quanLyBH = quanLyBH;
        //jlbBill.setText(Integer.toString(quanLyBH.getIdBill()));
        jtfidClient.setText(Integer.toString(quanLyBH.getIdClient()));
        jcbIdSmartPhone.addItem(quanLyBH.getIdSmartPhone());
        jcbIdSmartPhone.setSelectedItem(quanLyBH.getIdSmartPhone());
        jdcDate.setDate(quanLyBH.getDate());
        jspQuantityPurchased.setValue(quanLyBH.getQuantityPurchased());
        
        QuanLyBH clientInfo = findQuanLyBHByClientId(quanLyBHList, quanLyBH.getIdClient());
        if (clientInfo != null) {
                jtfNameClient.setText(clientInfo.getNameClient());
                jtfPnumberphoneClient.setText(clientInfo.getNumberPhoneClient());
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
    
    // tạo một getIdsSmartPhoneList vào trong quanlyBHDAOImpl rồi gắn cho interface quanLyBHService
    //gãy lười vcl
    private void initializeComboBox() {
        List<Integer> idsSmartPhoneList = quanLyBHService.getIdsSmartPhoneList();
        DefaultComboBoxModel<Integer> idSmartPhoneModel = new DefaultComboBoxModel<>(idsSmartPhoneList.toArray(new Integer[0]));
        jcbIdSmartPhone.setModel(idSmartPhoneModel);
    }
    
    public void setEvent() {
        btnSubmit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
              if(jtfNameClient.getText().length() == 0){
                   jlbCanhBao.setText("Vui lòng nhập đầy đủ thông tin");
                  System.out.println(".nhập cho đủ vào");
              } else {
                  //quanLyBH.setIdBill(Integer.parseInt(jtfidBill.getText()));
                  quanLyBH.setIdClient(Integer.parseInt(jtfidClient.getText()));
                  quanLyBH.setIdSmartPhone((int) jcbIdSmartPhone.getSelectedItem());
                  quanLyBH.setNameClient(jtfNameClient.getText());
                  quanLyBH.setNumberPhoneClient(jtfPnumberphoneClient.getText());
                  quanLyBH.setDate(covertDateToDateSql(jdcDate.getDate()));
                  quanLyBH.setQuantityPurchased((int) jspQuantityPurchased.getValue());
                  
                  int rowsAffected = quanLyBHService.create(quanLyBH);

                    if (rowsAffected > 0) {
                        quanLyBH.setIdBill(rowsAffected);
                        jlbBill.setText(Integer.toString(rowsAffected));
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
