/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.controller;

import com.priot4all.quanlybandienthoai.bean.ThongTinDTBean;
import com.priot4all.quanlybandienthoai.service.TrangChuService;
import com.priot4all.quanlybandienthoai.service.TrangChuServiceImpl;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author admin
 */
public class TrangChuController {
    private TrangChuService trangChuService = null;

    public TrangChuController() {
        trangChuService = new TrangChuServiceImpl();
    }
    
    public void setDataToChart1(JPanel jpnItem) {
        List<ThongTinDTBean> listItem = trangChuService.getListByThongTinDT();

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        if (listItem != null) {
            for (ThongTinDTBean item : listItem) {
                dataset.addValue(item.getSoluong(), "Số lượng", item.getMausac());
            }
        }

        JFreeChart barChart = ChartFactory.createBarChart(
                "Thống kê số lượng dựa trên màu sắc có trong kho".toUpperCase(),
                "Thời gian", "Màu sắc",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 321));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }
}
