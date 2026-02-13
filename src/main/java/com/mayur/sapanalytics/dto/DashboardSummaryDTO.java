package com.mayur.sapanalytics.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DashboardSummaryDTO {

    private long totalOrders;
    private long delayedOrders;

    private long lowStockProducts;
    private long overduePayments;

    private double riskScore;
}
