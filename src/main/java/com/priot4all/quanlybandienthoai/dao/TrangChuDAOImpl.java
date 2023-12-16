/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.dao;

import com.priot4all.quanlybandienthoai.bean.ThongTinDTBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class TrangChuDAOImpl implements TrangChuDao {

    @Override
    public List<ThongTinDTBean> getListByThongTinDT() {
        try {
        Connection cons = DBConnect.getConnection();
        String sql = "SELECT color, quantityInventory FROM ThongTinDT";
        List<ThongTinDTBean> list = new ArrayList<>();
        PreparedStatement ps = cons.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            ThongTinDTBean thongTinDTBean = new ThongTinDTBean();
            thongTinDTBean.setMausac(rs.getString("color"));
            thongTinDTBean.setSoluong(rs.getInt("quantityInventory"));

            list.add(thongTinDTBean);
        }
        ps.close();
        rs.close();
        cons.close();
        return list;
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return null;
    }
    
     
}
