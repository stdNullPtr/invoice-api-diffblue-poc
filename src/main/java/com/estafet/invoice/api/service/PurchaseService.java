package com.estafet.invoice.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.estafet.invoice.api.exception.ResourceNotFoundException;
import com.estafet.invoice.api.model.Purchase;
import com.estafet.invoice.api.repository.PurchaseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PurchaseService {

	private final PurchaseRepository purchaseRepository;

	public Purchase createPurchase(Purchase purchase) {
		return purchaseRepository.save(purchase);
	}

	public Purchase getPurchase(Long id) {
		return purchaseRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Purchase not found"));
	}

	public List<Purchase> getAllPurchases() {
		return purchaseRepository.findAll();
	}

	public Purchase updatePurchase(Long id, Purchase purchaseDetails) {
		Purchase purchase = getPurchase(id);
		purchase.setItem(purchaseDetails.getItem());
		purchase.setPrice(purchaseDetails.getPrice());
		purchase.setMerchant(purchaseDetails.getMerchant());
		return purchaseRepository.save(purchase);
	}

	public void deletePurchase(Long id) {
		purchaseRepository.deleteById(id);
	}
}
