package com.estafet.invoice.api.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estafet.invoice.api.model.Purchase;
import com.estafet.invoice.api.service.PdfGeneratorService;
import com.estafet.invoice.api.service.PurchaseService;
import com.itextpdf.text.DocumentException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/purchases")
public class PurchaseController {

	private final PurchaseService purchaseService;
	private final PdfGeneratorService pdfGeneratorService;

	@PostMapping
	public ResponseEntity<Purchase> createPurchase(@RequestBody Purchase purchase) {
		Purchase savedPurchase = purchaseService.createPurchase(purchase);
		return new ResponseEntity<>(savedPurchase, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Purchase> getPurchase(@PathVariable Long id) {
		Purchase purchase = purchaseService.getPurchase(id);
		return ResponseEntity.ok(purchase);
	}

	@GetMapping
	public ResponseEntity<List<Purchase>> getAllPurchases() {
		List<Purchase> purchases = purchaseService.getAllPurchases();
		return ResponseEntity.ok(purchases);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Purchase> updatePurchase(@PathVariable Long id, @RequestBody Purchase purchaseDetails) {
		Purchase updatedPurchase = purchaseService.updatePurchase(id, purchaseDetails);
		return ResponseEntity.ok(updatedPurchase);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePurchase(@PathVariable Long id) {
		purchaseService.deletePurchase(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/invoice/{purchaseId}")
	public ResponseEntity<byte[]> generateInvoiceForPurchase(@PathVariable Long purchaseId) throws DocumentException {
		byte[] pdfContent = pdfGeneratorService.generateInvoiceForPurchase(purchaseId);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_PDF);
		headers.add("Content-Disposition", "inline; filename=invoice.pdf");
		return new ResponseEntity<>(pdfContent, headers, HttpStatus.OK);
	}
}
