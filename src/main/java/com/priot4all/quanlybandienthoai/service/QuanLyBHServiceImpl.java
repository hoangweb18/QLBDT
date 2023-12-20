/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.service;

import com.priot4all.quanlybandienthoai.dao.QuanLyBHDAO;
import com.priot4all.quanlybandienthoai.dao.QuanLyBHDAOImpl;
import com.priot4all.quanlybandienthoai.model.QuanLyBH;
import java.util.List;

/**
 *
 * @author admin
 */
public class QuanLyBHServiceImpl implements QuanLyBHService {
    
    private QuanLyBHDAO quanLyBHDAO = null;
    
    public QuanLyBHServiceImpl () {
       quanLyBHDAO = new QuanLyBHDAOImpl();
    }

    @Override
    public List<QuanLyBH> getList() {
        return quanLyBHDAO.getList();
    }
    @Override
    public List<QuanLyBH> getAllLists() {
        return quanLyBHDAO.getAllLists();
    }
    @Override
    public List<Integer> getIdsSmartPhoneList(){
        return quanLyBHDAO.getIdsSmartPhoneList();
    }
    
    @Override
    public List<Integer> getIdClientList() {
        return quanLyBHDAO.getIdClientList();
    }
    
    @Override
    public int create(QuanLyBH quanLyBH){
        return quanLyBHDAO.create(quanLyBH);
    }
    
    @Override
    public int updateBill(QuanLyBH quanLyBH) {
        return quanLyBHDAO.updateBill(quanLyBH);
    }
    
    @Override
    public int createBill(QuanLyBH quanLyBH) {
        return quanLyBHDAO.createBill(quanLyBH);
    }
    
    @Override
    public int deleteRecord(QuanLyBH quanLyBH) {
        return quanLyBHDAO.deleteRecord(quanLyBH);
    }
}
