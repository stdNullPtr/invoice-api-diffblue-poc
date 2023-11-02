package com.estafet.invoice.api.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estafet.invoice.api.model.Merchant;
import com.estafet.invoice.api.service.MerchantService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/merchants")
public class MerchantController {

	private final MerchantService merchantService;

	@PostMapping
	public ResponseEntity<Merchant> createMerchant(@RequestBody Merchant merchant) {
		Merchant savedMerchant = merchantService.createMerchant(merchant);
		return new ResponseEntity<>(savedMerchant, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Merchant> getMerchant(@PathVariable Long id) {
		Merchant merchant = merchantService.getMerchant(id);
		return ResponseEntity.ok(merchant);
	}

	@GetMapping
	public ResponseEntity<List<Merchant>> getAllMerchants() {
		List<Merchant> merchants = merchantService.getAllMerchants();
		return ResponseEntity.ok(merchants);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Merchant> updateMerchant(@PathVariable Long id, @RequestBody Merchant merchantDetails) {
		Merchant updatedMerchant = merchantService.updateMerchant(id, merchantDetails);
		return ResponseEntity.ok(updatedMerchant);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteMerchant(@PathVariable Long id) {
		merchantService.deleteMerchant(id);
		return ResponseEntity.noContent().build();
	}
}
