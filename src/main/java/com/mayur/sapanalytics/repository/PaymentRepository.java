package com.mayur.sapanalytics.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mayur.sapanalytics.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
