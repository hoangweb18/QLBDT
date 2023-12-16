/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.service;

import com.priot4all.quanlybandienthoai.dao.ThongTinDTDAO;
import com.priot4all.quanlybandienthoai.dao.ThongTinDTDAOImpl;
import com.priot4all.quanlybandienthoai.model.ThongTinDT;
import java.util.List;

/**
 *
 * @author admin
 */
public class ThongTinDTServiceImpl implements ThongTinDTService {
    
    private ThongTinDTDAO thongTinDTDAO = null;
    
    public ThongTinDTServiceImpl() {
        thongTinDTDAO = new ThongTinDTDAOImpl();
    }

    @Override
    public List<ThongTinDT> getList() {
        return thongTinDTDAO.getList();
    }

    @Override
    public int createOrUpdate(ThongTinDT thongTinDT) {
        return thongTinDTDAO.createOrUpdate(thongTinDT);
    }
    @Override
    public int deleteRecord(ThongTinDT thongTinDT){
        return thongTinDTDAO.deleteRecord(thongTinDT);
    }
}
