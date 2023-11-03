package com.estafet.invoice.api.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = { HomeController.class })
@ExtendWith(SpringExtension.class)
class HomeControllerDiffblueTest {

	@Autowired
	private HomeController homeController;

	/**
	 * Method under test: {@link HomeController#redirectToSwagger()}
	 */
	@Test
	void testRedirectToSwagger() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
		MockMvcBuilders.standaloneSetup(homeController)
				.build()
				.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.model().size(0))
				.andExpect(MockMvcResultMatchers.view().name("redirect:/swagger-ui.html"))
				.andExpect(MockMvcResultMatchers.redirectedUrl("/swagger-ui.html"));
	}

	/**
	 * Method under test: {@link HomeController#redirectToSwagger()}
	 */
	@Test
	void testRedirectToSwagger2() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/", "Uri Variables");
		MockMvcBuilders.standaloneSetup(homeController)
				.build()
				.perform(requestBuilder)
				.andExpect(MockMvcResultMatchers.status().isFound())
				.andExpect(MockMvcResultMatchers.model().size(0))
				.andExpect(MockMvcResultMatchers.view().name("redirect:/swagger-ui.html"))
				.andExpect(MockMvcResultMatchers.redirectedUrl("/swagger-ui.html"));
	}
}

