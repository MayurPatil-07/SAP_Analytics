package com.mayur.sapanalytics.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mayur.sapanalytics.dto.DashboardSummaryDTO;
import com.mayur.sapanalytics.entity.Inventory;
import com.mayur.sapanalytics.entity.Order;
import com.mayur.sapanalytics.entity.Payment;
import com.mayur.sapanalytics.service.AnalyticsService;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/delayed-orders")
    public List<Order> getDelayedOrders() {
        return analyticsService.getDelayedOrders();
    }

    @GetMapping("/low-stock")
    public List<Inventory> getLowStockProducts() {
        return analyticsService.getLowStockProducts();
    }

    @GetMapping("/overdue-payments")
    public List<Payment> getOverduePayments() {
        return analyticsService.getOverduePayments();
    }
    @GetMapping("/dashboard-summary")
    public DashboardSummaryDTO getSummary() {
        return analyticsService.getDashboardSummary();
    }

    @GetMapping("/recommendations")
    public List<String> getRecommendations() {
        return analyticsService.generateRecommendations();
    }

}
