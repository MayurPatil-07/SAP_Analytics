package com.mayur.sapanalytics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mayur.sapanalytics.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

