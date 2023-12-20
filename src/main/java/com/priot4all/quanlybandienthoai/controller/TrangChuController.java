/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.priot4all.quanlybandienthoai.controller;

import com.priot4all.quanlybandienthoai.bean.QuanLyBHBean;
import com.priot4all.quanlybandienthoai.bean.ThongTinDTBean;
import com.priot4all.quanlybandienthoai.service.TrangChuService;
import com.priot4all.quanlybandienthoai.service.TrangChuServiceImpl;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.AxisLocation;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.plot.DatasetRenderingOrder;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author admin
 */
public class TrangChuController {
    private JLabel jlbTonKho;
    private JLabel jlbTongDoanhThu;
    private JLabel jlbTongKhachHang;
    private JLabel jlbTongDonHang;
    
    private TrangChuService trangChuService = null;

    public TrangChuController(JLabel jlbTonKho,JLabel jlbTongDoanhThu,JLabel jlbTongDonHang,JLabel jlbTongKhachHang) {
        this.jlbTonKho = jlbTonKho;
        this.jlbTongDoanhThu = jlbTongDoanhThu;
        this.jlbTongDonHang = jlbTongDonHang;
        this.jlbTongKhachHang = jlbTongKhachHang;
        trangChuService = new TrangChuServiceImpl();
    }
    
    public void setView(){
        int totalQuantity = trangChuService.getQuantityInventory();
        double totalRevenue = trangChuService.getTotalRevenue();
        int totalOrders = trangChuService.getTotalOrders();
        int totalCustomers = trangChuService.getTotalCustomers();
        jlbTonKho.setText("Tổng số lượng tồn kho: " + totalQuantity);
        jlbTongDoanhThu.setText("Tổng doanh thu: " + totalRevenue);
        jlbTongDonHang.setText("Tổng số đơn hàng: " + totalOrders);
        jlbTongKhachHang.setText("Tổng số khách hàng: " + totalCustomers);
        
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
                "Màu sắc", "Số lượng",
                dataset, PlotOrientation.VERTICAL, false, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 321));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }
    
    public void setDataToChart2(JPanel jpnItem) {
        List<QuanLyBHBean> dataList = trangChuService.getListByQuanLyBHMonth();

        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();
        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();

        for (QuanLyBHBean data : dataList) {
            dataset1.addValue(data.getSoluongban(), "Số lượng", data.getYearmonth());
            dataset2.addValue(data.getDoanhThu(), "Doanh thu", data.getYearmonth());
        }

        JFreeChart chart = ChartFactory.createLineChart(
                "Biểu đồ Doanh thu và Số lượng bán ra theo tháng",
                "Tháng",
                "Số lượng",
                dataset1,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        CategoryPlot plot1 = chart.getCategoryPlot();
        plot1.setRangePannable(true);

        LineAndShapeRenderer renderer1 = new LineAndShapeRenderer();
        plot1.setRenderer(renderer1);

        CategoryAxis domainAxis = new CategoryAxis("Tháng");

        CategoryPlot plot2 = new CategoryPlot(dataset2, domainAxis, new NumberAxis("Doanh thu"), new BarRenderer());
        plot2.setDomainGridlinesVisible(true);
        plot2.setRangePannable(true);

        CombinedDomainCategoryPlot combinedPlot = new CombinedDomainCategoryPlot(domainAxis);
        combinedPlot.add(plot1, 1);
        combinedPlot.add(plot2, 1);

        chart.getCategoryPlot().setDomainGridlinesVisible(true);
        chart.getCategoryPlot().setRangePannable(true);
        chart.getCategoryPlot().setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);

        chart = new JFreeChart("Thống kê số lượng bán ra và doanh thu theo tháng", JFreeChart.DEFAULT_TITLE_FONT, combinedPlot, true);

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), 321));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }
}
