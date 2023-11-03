package com.estafet.invoice.api.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.estafet.invoice.api.model.Merchant;
import com.estafet.invoice.api.model.Purchase;
import com.estafet.invoice.api.service.PdfGeneratorService;
import com.estafet.invoice.api.service.PurchaseService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = { PurchaseController.class })
@ExtendWith(SpringExtension.class)
class PurchaseControllerDiffblueTest {

	@MockBean
	private PdfGeneratorService pdfGeneratorService;

	@Autowired
	private PurchaseController purchaseController;

	@MockBean
	private PurchaseService purchaseService;

	/**
	 * Method under test: {@link PurchaseController#getPurchase(Long)}
	 */
	@Test
	void testGetPurchase() throws Exception {
		Merchant merchant = new Merchant();
		merchant.setAddress("42 Main St");
		merchant.setId(1L);
		merchant.setName("Name");

		Purchase purchase = new Purchase();
		purchase.setId(1L);
		purchase.setItem("Item");
		purchase.setMerchant(merchant);
		purchase.setPrice(10.0d);
		purchase.setPurchaseDate(LocalDate.of(1970, 1, 1));
		when(purchaseService.getPurchase(Mockito.<Long>any())).thenReturn(purchase);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/purchases/{id}", 1L);
		MockMvcBuilders.standaloneSetup(purchaseController)
				.build()
				.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.content()
						.string(
								"{\"id\":1,\"item\":\"Item\",\"price\":10.0,\"merchant\":{\"id\":1,\"name\":\"Name\",\"address\":\"42 Main St\"},\"purchaseDate"
										+ "\":[1970,1,1]}"));
	}

	/**
	 * Method under test: {@link PurchaseController#getAllPurchases()}
	 */
	@Test
	void testGetAllPurchases() throws Exception {
		when(purchaseService.getAllPurchases()).thenReturn(new ArrayList<>());
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/purchases");
		MockMvcBuilders.standaloneSetup(purchaseController)
				.build()
				.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.content().string("[]"));
	}

	/**
	 * Method under test: {@link PurchaseController#updatePurchase(Long, Purchase)}
	 */
	@Test
	void testUpdatePurchase() throws Exception {
		Merchant merchant = new Merchant();
		merchant.setAddress("42 Main St");
		merchant.setId(1L);
		merchant.setName("Name");

		Purchase purchase = new Purchase();
		purchase.setId(1L);
		purchase.setItem("Item");
		purchase.setMerchant(merchant);
		purchase.setPrice(10.0d);
		purchase.setPurchaseDate(LocalDate.of(1970, 1, 1));
		when(purchaseService.updatePurchase(Mockito.<Long>any(), Mockito.<Purchase>any())).thenReturn(purchase);

		Merchant merchant2 = new Merchant();
		merchant2.setAddress("42 Main St");
		merchant2.setId(1L);
		merchant2.setName("Name");

		Purchase purchase2 = new Purchase();
		purchase2.setId(1L);
		purchase2.setItem("Item");
		purchase2.setMerchant(merchant2);
		purchase2.setPrice(10.0d);
		purchase2.setPurchaseDate(null);
		String content = (new ObjectMapper()).writeValueAsString(purchase2);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/purchases/{id}", 1L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(content);
		MockMvcBuilders.standaloneSetup(purchaseController)
				.build()
				.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.content()
						.string(
								"{\"id\":1,\"item\":\"Item\",\"price\":10.0,\"merchant\":{\"id\":1,\"name\":\"Name\",\"address\":\"42 Main St\"},\"purchaseDate"
										+ "\":[1970,1,1]}"));
	}

	/**
	 * Method under test: {@link PurchaseController#createPurchase(Purchase)}
	 */
	@Test
	void testCreatePurchase() throws Exception {
		when(purchaseService.getAllPurchases()).thenReturn(new ArrayList<>());

		Merchant merchant = new Merchant();
		merchant.setAddress("42 Main St");
		merchant.setId(1L);
		merchant.setName("Name");

		Purchase purchase = new Purchase();
		purchase.setId(1L);
		purchase.setItem("Item");
		purchase.setMerchant(merchant);
		purchase.setPrice(10.0d);
		purchase.setPurchaseDate(null);
		String content = (new ObjectMapper()).writeValueAsString(purchase);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/purchases")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content);
		MockMvcBuilders.standaloneSetup(purchaseController)
				.build()
				.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.content().string("[]"));
	}

	/**
	 * Method under test: {@link PurchaseController#deletePurchase(Long)}
	 */
	@Test
	void testDeletePurchase() throws Exception {
		doNothing().when(purchaseService).deletePurchase(Mockito.<Long>any());
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/purchases/{id}", 1L);
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(purchaseController)
				.build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
	}

	/**
	 * Method under test: {@link PurchaseController#deletePurchase(Long)}
	 */
	@Test
	void testDeletePurchase2() throws Exception {
		doNothing().when(purchaseService).deletePurchase(Mockito.<Long>any());
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/purchases/{id}", 1L);
		requestBuilder.contentType("https://example.org/example");
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(purchaseController)
				.build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
	}

	/**
	 * Method under test: {@link PurchaseController#generateInvoiceForPurchase(Long)}
	 */
	@Test
	void testGenerateInvoiceForPurchase() throws Exception {
		when(pdfGeneratorService.generateInvoiceForPurchase(Mockito.<Long>any()))
				.thenReturn("AXAXAXAX".getBytes("UTF-8"));
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/purchases/invoice/{purchaseId}",
				1L);
		MockMvcBuilders.standaloneSetup(purchaseController)
				.build()
				.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/pdf"))
				.andExpect(MockMvcResultMatchers.content().string("AXAXAXAX"));
	}
}

