/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.controller;

import com.priot4all.quanlybandienthoai.model.TaiKhoan;
import com.priot4all.quanlybandienthoai.service.TaiKhoanService;
import com.priot4all.quanlybandienthoai.service.TaiKhoanServiceImpl;
import com.priot4all.quanlybandienthoai.view.MainJFrame;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author admin
 */
public class LoginController {
    private Dialog dialog;
    private JButton btnSubmit;
    private JTextField jtfTenDangNhap;
    private JTextField jtfMatKhau;
    private JLabel jlbMsg;

    private TaiKhoanService taiKhoanService = null;

    public LoginController(Dialog dialog, JButton btnSubmit,
            JTextField jtfTenDangNhap, JTextField jtfMatKhau, JLabel jlbMsg) {
        this.dialog = dialog;
        this.btnSubmit = btnSubmit;
        this.jtfTenDangNhap = jtfTenDangNhap;
        this.jtfMatKhau = jtfMatKhau;
        this.jlbMsg = jlbMsg;

        taiKhoanService = new TaiKhoanServiceImpl();
    }

    public void setEvent() {
        btnSubmit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (jtfTenDangNhap.getText().length() == 0
                            || jtfMatKhau.getText().length() == 0) {
                        jlbMsg.setText("Vui lòng nhập dữ liệu bắt buộc!");
                    } else {
                        TaiKhoan taiKhoan = taiKhoanService.login(jtfTenDangNhap.getText(), jtfMatKhau.getText());
                        if (taiKhoan == null) {
                            jlbMsg.setText("Tên đăng nhập và mật khẩu không đúng!");
                        } else {
                            if (!taiKhoan.isTinhtrang()) {
                                jlbMsg.setText("Tài khoản của bạn đang bị tạm khóa!");
                            } else {
                                MainJFrame frame = new MainJFrame();
                                frame.setTitle("Quản Lý bán điện thoại");
                                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                                frame.setVisible(true);
                                dialog.dispose();
                            }
                        }
                    }
                } catch (Exception ex) {
                    jlbMsg.setText(ex.toString());
                }
            }
        });

    }
}

