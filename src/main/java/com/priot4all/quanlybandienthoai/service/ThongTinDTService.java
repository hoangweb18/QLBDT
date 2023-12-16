/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.service;

import com.priot4all.quanlybandienthoai.model.ThongTinDT;
import java.util.List;


/**
 *
 * @author admin
 */
public interface ThongTinDTService {
    
    public List<ThongTinDT> getList();
    
    public int createOrUpdate(ThongTinDT thongTinDT);
    
    public int deleteRecord(ThongTinDT thongTinDT);
}
