/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.main;

import com.priot4all.quanlybandienthoai.view.LoginDialog;
import com.priot4all.quanlybandienthoai.view.MainJFrame;

/**
 *
 * @author admin
 */
public class Main {
    public static void main(String[] args){
    //new MainJFrame().setVisible(true);
        LoginDialog dialog = new LoginDialog(null, true);
        dialog.setTitle("Đăng Nhập Hệ thống");
        dialog.setResizable(false);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }
}
