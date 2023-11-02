package com.estafet.invoice.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estafet.invoice.api.model.Merchant;

public interface MerchantRepository extends JpaRepository<Merchant, Long> {}
