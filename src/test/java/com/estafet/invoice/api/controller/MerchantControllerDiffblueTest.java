package com.estafet.invoice.api.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.estafet.invoice.api.model.Merchant;
import com.estafet.invoice.api.service.MerchantService;
import com.fasterxml.jackson.databind.ObjectMapper;

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

@ContextConfiguration(classes = { MerchantController.class })
@ExtendWith(SpringExtension.class)
class MerchantControllerDiffblueTest {

	@Autowired
	private MerchantController merchantController;

	@MockBean
	private MerchantService merchantService;

	/**
	 * Method under test: {@link MerchantController#getMerchant(Long)}
	 */
	@Test
	void testGetMerchant() throws Exception {
		Merchant merchant = new Merchant();
		merchant.setAddress("42 Main St");
		merchant.setId(1L);
		merchant.setName("Name");
		when(merchantService.getMerchant(Mockito.<Long>any())).thenReturn(merchant);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/merchants/{id}", 1L);
		MockMvcBuilders.standaloneSetup(merchantController)
				.build()
				.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"name\":\"Name\",\"address\":\"42 Main St\"}"));
	}

	/**
	 * Method under test: {@link MerchantController#getAllMerchants()}
	 */
	@Test
	void testGetAllMerchants() throws Exception {
		when(merchantService.getAllMerchants()).thenReturn(new ArrayList<>());
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/merchants");
		MockMvcBuilders.standaloneSetup(merchantController)
				.build()
				.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.content().string("[]"));
	}

	/**
	 * Method under test: {@link MerchantController#updateMerchant(Long, Merchant)}
	 */
	@Test
	void testUpdateMerchant() throws Exception {
		Merchant merchant = new Merchant();
		merchant.setAddress("42 Main St");
		merchant.setId(1L);
		merchant.setName("Name");
		when(merchantService.updateMerchant(Mockito.<Long>any(), Mockito.<Merchant>any())).thenReturn(merchant);

		Merchant merchant2 = new Merchant();
		merchant2.setAddress("42 Main St");
		merchant2.setId(1L);
		merchant2.setName("Name");
		String content = (new ObjectMapper()).writeValueAsString(merchant2);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/merchants/{id}", 1L)
				.contentType(MediaType.APPLICATION_JSON)
				.content(content);
		MockMvcBuilders.standaloneSetup(merchantController)
				.build()
				.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"name\":\"Name\",\"address\":\"42 Main St\"}"));
	}

	/**
	 * Method under test: {@link MerchantController#createMerchant(Merchant)}
	 */
	@Test
	void testCreateMerchant() throws Exception {
		when(merchantService.getAllMerchants()).thenReturn(new ArrayList<>());

		Merchant merchant = new Merchant();
		merchant.setAddress("42 Main St");
		merchant.setId(1L);
		merchant.setName("Name");
		String content = (new ObjectMapper()).writeValueAsString(merchant);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/merchants")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content);
		MockMvcBuilders.standaloneSetup(merchantController)
				.build()
				.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.content().string("[]"));
	}

	/**
	 * Method under test: {@link MerchantController#deleteMerchant(Long)}
	 */
	@Test
	void testDeleteMerchant() throws Exception {
		doNothing().when(merchantService).deleteMerchant(Mockito.<Long>any());
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/merchants/{id}", 1L);
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(merchantController)
				.build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
	}

	/**
	 * Method under test: {@link MerchantController#deleteMerchant(Long)}
	 */
	@Test
	void testDeleteMerchant2() throws Exception {
		doNothing().when(merchantService).deleteMerchant(Mockito.<Long>any());
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/merchants/{id}", 1L);
		requestBuilder.contentType("https://example.org/example");
		ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(merchantController)
				.build()
				.perform(requestBuilder);
		actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
}

