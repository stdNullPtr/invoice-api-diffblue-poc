package com.estafet.invoice.api.service;

import org.springframework.stereotype.Service;

import com.estafet.invoice.api.model.Purchase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PdfGeneratorService {
    public byte[] generateInvoiceForPurchase(Purchase purchase) {
        // Use iText to generate a PDF based on the purchase details
		return new byte[0];
	}
}
