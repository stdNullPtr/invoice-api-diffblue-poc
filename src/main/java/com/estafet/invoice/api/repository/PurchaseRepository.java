package com.estafet.invoice.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estafet.invoice.api.model.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {}
