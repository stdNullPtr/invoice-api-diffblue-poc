package com.estafet.invoice.api.service;

import java.io.ByteArrayOutputStream;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.estafet.invoice.api.model.Merchant;
import com.estafet.invoice.api.model.Purchase;
import com.estafet.invoice.api.repository.MerchantRepository;
import com.estafet.invoice.api.repository.PurchaseRepository;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
@Service
public class PdfGeneratorService {

	private final MerchantRepository merchantRepository;
	private final PurchaseRepository purchaseRepository;

	@SneakyThrows
	public byte[] generateInvoiceForPurchase(Long purchaseId) {
		Optional<Purchase> optionalPurchase = purchaseRepository.findById(purchaseId);
		if (optionalPurchase.isEmpty()) {
			throw new RuntimeException("Purchase not found");
		}

		Purchase purchase = optionalPurchase.get();
		Optional<Merchant> optionalMerchant = merchantRepository.findById(purchase.getMerchant().getId());
		if (optionalMerchant.isEmpty()) {
			throw new RuntimeException("Merchant not found for the purchase");
		}

		Merchant merchant = optionalMerchant.get();

		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		Document document = new Document();
		PdfWriter.getInstance(document, byteArrayOutputStream);

		document.open();

		// Adding Invoice Header
		Font headerFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
		Paragraph header = new Paragraph("INVOICE", headerFont);
		header.setAlignment(Element.ALIGN_CENTER);
		document.add(header);

		// Adding Merchant Details
		document.add(new Paragraph("Merchant: " + merchant.getName()));
		document.add(new Paragraph("Address: " + merchant.getAddress()));

		// Adding Purchase Details
		document.add(new Paragraph("Item: " + purchase.getItem()));
		document.add(new Paragraph("Price: " + purchase.getPrice()));
		document.add(new Paragraph("Purchase Date: " + purchase.getPurchaseDate()));

		document.close();

		return byteArrayOutputStream.toByteArray();
	}
}
