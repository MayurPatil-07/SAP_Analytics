package com.mayur.sapanalytics.service;

import java.util.ArrayList;
import java.util.List;

//import org.hibernate.validator.internal.util.logging.LoggerFactry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mayur.sapanalytics.dto.DashboardSummaryDTO;
import com.mayur.sapanalytics.entity.Inventory;
import com.mayur.sapanalytics.entity.Order;
import com.mayur.sapanalytics.entity.Payment;
import com.mayur.sapanalytics.repository.InventoryRepository;
import com.mayur.sapanalytics.repository.OrderRepository;
import com.mayur.sapanalytics.repository.PaymentRepository;

//import ch.qos.logback.classic.Logger;

@Service
public class AnalyticsService {
	

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    public List<Order> getDelayedOrders() {
        return orderRepository.findAll()
                .stream()
                .filter(order ->
                        order.getDeliveryDate().isAfter(order.getOrderDate().plusDays(3)))
                .toList();
    }

    public List<Inventory> getLowStockProducts() {
        return inventoryRepository.findAll()
                .stream()
                .filter(inv -> inv.getAvailableStock() < inv.getReorderLevel())
                .toList();
    }

    public List<Payment> getOverduePayments() {
        return paymentRepository.findAll()
                .stream()
                .filter(payment ->
                        payment.getPaymentDate() == null ||
                        payment.getPaymentDate().isAfter(payment.getDueDate()))
                .toList();
    }
    
    public DashboardSummaryDTO getDashboardSummary() {
    	logger.info("Calculating dashboard summary");
        long totalOrders = orderRepository.count();

        long delayedOrders = getDelayedOrders().size();
        long lowStock = getLowStockProducts().size();
        long overduePayments = getOverduePayments().size();

        double riskScore = calculateRiskScore(delayedOrders, lowStock, overduePayments);

        return new DashboardSummaryDTO(
                totalOrders,
                delayedOrders,
                lowStock,
                overduePayments,
                riskScore
        );
    }
    private double calculateRiskScore(long delayed, long stock, long payments) {

        int weightDelay = 3;
        int weightStock = 2;
        int weightPayment = 4;

        return (delayed * weightDelay +
                stock * weightStock +
                payments * weightPayment) * 1.0;
    }
    
    public List<String> generateRecommendations() {

        List<String> recommendations = new ArrayList<>();

        if (!getDelayedOrders().isEmpty()) {
            recommendations.add("Improve supply chain coordination to reduce delivery delays.");
        }

        if (!getLowStockProducts().isEmpty()) {
            recommendations.add("Increase safety stock levels to avoid inventory shortages.");
        }

        if (!getOverduePayments().isEmpty()) {
            recommendations.add("Strengthen credit control and customer follow-ups to reduce payment delays.");
        }

        return recommendations;
    }
    private static final Logger logger =
            LoggerFactory.getLogger(AnalyticsService.class);
    		


}

