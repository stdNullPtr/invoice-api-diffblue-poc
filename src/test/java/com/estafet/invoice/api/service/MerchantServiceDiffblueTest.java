package com.estafet.invoice.api.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.estafet.invoice.api.exception.ResourceNotFoundException;
import com.estafet.invoice.api.model.Merchant;
import com.estafet.invoice.api.repository.MerchantRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = { MerchantService.class })
@ExtendWith(SpringExtension.class)
class MerchantServiceDiffblueTest {

	@MockBean
	private MerchantRepository merchantRepository;

	@Autowired
	private MerchantService merchantService;

	/**
	 * Method under test: {@link MerchantService#createMerchant(Merchant)}
	 */
	@Test
	void testCreateMerchant() {
		Merchant merchant = new Merchant();
		merchant.setAddress("42 Main St");
		merchant.setId(1L);
		merchant.setName("Name");
		when(merchantRepository.save(Mockito.any())).thenReturn(merchant);

		Merchant merchant2 = new Merchant();
		merchant2.setAddress("42 Main St");
		merchant2.setId(1L);
		merchant2.setName("Name");
		Merchant actualCreateMerchantResult = merchantService.createMerchant(merchant2);
		verify(merchantRepository).save(Mockito.any());
		assertSame(merchant, actualCreateMerchantResult);
	}

	/**
	 * Method under test: {@link MerchantService#createMerchant(Merchant)}
	 */
	@Test
	void testCreateMerchant2() {
		when(merchantRepository.save(Mockito.any()))
				.thenThrow(new ResourceNotFoundException("An error occurred"));

		Merchant merchant = new Merchant();
		merchant.setAddress("42 Main St");
		merchant.setId(1L);
		merchant.setName("Name");
		assertThrows(ResourceNotFoundException.class, () -> merchantService.createMerchant(merchant));
		verify(merchantRepository).save(Mockito.any());
	}

	/**
	 * Method under test: {@link MerchantService#createMerchant(Merchant)}
	 */
	@Test
	void testCreateMerchant3() {
		Merchant merchant = new Merchant();
		merchant.setAddress("42 Main St");
		merchant.setId(1L);
		merchant.setName("Name");
		when(merchantRepository.save(Mockito.<Merchant>any())).thenReturn(merchant);

		Merchant merchant2 = new Merchant();
		merchant2.setAddress("42 Main St");
		merchant2.setId(1L);
		merchant2.setName("Name");
		Merchant actualCreateMerchantResult = merchantService.createMerchant(merchant2);
		verify(merchantRepository).save(Mockito.<Merchant>any());
		assertSame(merchant, actualCreateMerchantResult);
	}

	/**
	 * Method under test: {@link MerchantService#createMerchant(Merchant)}
	 */
	@Test
	void testCreateMerchant4() {
		when(merchantRepository.save(Mockito.<Merchant>any()))
				.thenThrow(new ResourceNotFoundException("An error occurred"));

		Merchant merchant = new Merchant();
		merchant.setAddress("42 Main St");
		merchant.setId(1L);
		merchant.setName("Name");
		assertThrows(ResourceNotFoundException.class, () -> merchantService.createMerchant(merchant));
		verify(merchantRepository).save(Mockito.<Merchant>any());
	}

	/**
	 * Method under test: {@link MerchantService#getMerchant(Long)}
	 */
	@Test
	void testGetMerchant() {
		Merchant merchant = new Merchant();
		merchant.setAddress("42 Main St");
		merchant.setId(1L);
		merchant.setName("Name");
		Optional<Merchant> ofResult = Optional.of(merchant);
		when(merchantRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
		Merchant actualMerchant = merchantService.getMerchant(1L);
		verify(merchantRepository).findById(Mockito.<Long>any());
		assertSame(merchant, actualMerchant);
	}

	/**
	 * Method under test: {@link MerchantService#getMerchant(Long)}
	 */
	@Test
	void testGetMerchant2() {
		Optional<Merchant> emptyResult = Optional.empty();
		when(merchantRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
		assertThrows(ResourceNotFoundException.class, () -> merchantService.getMerchant(1L));
		verify(merchantRepository).findById(Mockito.<Long>any());
	}

	/**
	 * Method under test: {@link MerchantService#getMerchant(Long)}
	 */
	@Test
	void testGetMerchant3() {
		when(merchantRepository.findById(Mockito.<Long>any()))
				.thenThrow(new ResourceNotFoundException("An error occurred"));
		assertThrows(ResourceNotFoundException.class, () -> merchantService.getMerchant(1L));
		verify(merchantRepository).findById(Mockito.<Long>any());
	}

	/**
	 * Method under test: {@link MerchantService#getAllMerchants()}
	 */
	@Test
	void testGetAllMerchants() {
		ArrayList<Merchant> merchantList = new ArrayList<>();
		when(merchantRepository.findAll()).thenReturn(merchantList);
		List<Merchant> actualAllMerchants = merchantService.getAllMerchants();
		verify(merchantRepository).findAll();
		assertTrue(actualAllMerchants.isEmpty());
		assertSame(merchantList, actualAllMerchants);
	}

	/**
	 * Method under test: {@link MerchantService#getAllMerchants()}
	 */
	@Test
	void testGetAllMerchants2() {
		when(merchantRepository.findAll()).thenThrow(new ResourceNotFoundException("An error occurred"));
		assertThrows(ResourceNotFoundException.class, () -> merchantService.getAllMerchants());
		verify(merchantRepository).findAll();
	}

	/**
	 * Method under test: {@link MerchantService#updateMerchant(Long, Merchant)}
	 */
	@Test
	void testUpdateMerchant() {
		Merchant merchant = new Merchant();
		merchant.setAddress("42 Main St");
		merchant.setId(1L);
		merchant.setName("Name");
		Optional<Merchant> ofResult = Optional.of(merchant);

		Merchant merchant2 = new Merchant();
		merchant2.setAddress("42 Main St");
		merchant2.setId(1L);
		merchant2.setName("Name");
		when(merchantRepository.save(Mockito.any())).thenReturn(merchant2);
		when(merchantRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

		Merchant merchantDetails = new Merchant();
		merchantDetails.setAddress("42 Main St");
		merchantDetails.setId(1L);
		merchantDetails.setName("Name");
		Merchant actualUpdateMerchantResult = merchantService.updateMerchant(1L, merchantDetails);
		verify(merchantRepository).findById(Mockito.<Long>any());
		verify(merchantRepository).save(Mockito.any());
		assertSame(merchant2, actualUpdateMerchantResult);
	}

	/**
	 * Method under test: {@link MerchantService#updateMerchant(Long, Merchant)}
	 */
	@Test
	void testUpdateMerchant2() {
		Merchant merchant = new Merchant();
		merchant.setAddress("42 Main St");
		merchant.setId(1L);
		merchant.setName("Name");
		Optional<Merchant> ofResult = Optional.of(merchant);
		when(merchantRepository.save(Mockito.any()))
				.thenThrow(new ResourceNotFoundException("An error occurred"));
		when(merchantRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

		Merchant merchantDetails = new Merchant();
		merchantDetails.setAddress("42 Main St");
		merchantDetails.setId(1L);
		merchantDetails.setName("Name");
		assertThrows(ResourceNotFoundException.class, () -> merchantService.updateMerchant(1L, merchantDetails));
		verify(merchantRepository).findById(Mockito.<Long>any());
		verify(merchantRepository).save(Mockito.any());
	}

	/**
	 * Method under test: {@link MerchantService#updateMerchant(Long, Merchant)}
	 */
	@Test
	void testUpdateMerchant3() {
		Optional<Merchant> emptyResult = Optional.empty();
		when(merchantRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

		Merchant merchantDetails = new Merchant();
		merchantDetails.setAddress("42 Main St");
		merchantDetails.setId(1L);
		merchantDetails.setName("Name");
		assertThrows(ResourceNotFoundException.class, () -> merchantService.updateMerchant(1L, merchantDetails));
		verify(merchantRepository).findById(Mockito.<Long>any());
	}

	/**
	 * Method under test: {@link MerchantService#updateMerchant(Long, Merchant)}
	 */
	@Test
	void testUpdateMerchant4() {
		Merchant merchant = new Merchant();
		merchant.setAddress("42 Main St");
		merchant.setId(1L);
		merchant.setName("Name");
		Optional<Merchant> ofResult = Optional.of(merchant);

		Merchant merchant2 = new Merchant();
		merchant2.setAddress("42 Main St");
		merchant2.setId(1L);
		merchant2.setName("Name");
		when(merchantRepository.save(Mockito.<Merchant>any())).thenReturn(merchant2);
		when(merchantRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

		Merchant merchantDetails = new Merchant();
		merchantDetails.setAddress("42 Main St");
		merchantDetails.setId(1L);
		merchantDetails.setName("Name");
		Merchant actualUpdateMerchantResult = merchantService.updateMerchant(1L, merchantDetails);
		verify(merchantRepository).findById(Mockito.<Long>any());
		verify(merchantRepository).save(Mockito.<Merchant>any());
		assertSame(merchant2, actualUpdateMerchantResult);
	}

	/**
	 * Method under test: {@link MerchantService#updateMerchant(Long, Merchant)}
	 */
	@Test
	void testUpdateMerchant5() {
		Merchant merchant = new Merchant();
		merchant.setAddress("42 Main St");
		merchant.setId(1L);
		merchant.setName("Name");
		Optional<Merchant> ofResult = Optional.of(merchant);
		when(merchantRepository.save(Mockito.<Merchant>any()))
				.thenThrow(new ResourceNotFoundException("An error occurred"));
		when(merchantRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

		Merchant merchantDetails = new Merchant();
		merchantDetails.setAddress("42 Main St");
		merchantDetails.setId(1L);
		merchantDetails.setName("Name");
		assertThrows(ResourceNotFoundException.class, () -> merchantService.updateMerchant(1L, merchantDetails));
		verify(merchantRepository).findById(Mockito.<Long>any());
		verify(merchantRepository).save(Mockito.<Merchant>any());
	}

	/**
	 * Method under test: {@link MerchantService#deleteMerchant(Long)}
	 */
	@Test
	void testDeleteMerchant() {
		doNothing().when(merchantRepository).deleteById(Mockito.<Long>any());
		merchantService.deleteMerchant(1L);
		verify(merchantRepository).deleteById(Mockito.<Long>any());
	}

	/**
	 * Method under test: {@link MerchantService#deleteMerchant(Long)}
	 */
	@Test
	void testDeleteMerchant2() {
		doThrow(new ResourceNotFoundException("An error occurred")).when(merchantRepository)
				.deleteById(Mockito.<Long>any());
		assertThrows(ResourceNotFoundException.class, () -> merchantService.deleteMerchant(1L));
		verify(merchantRepository).deleteById(Mockito.<Long>any());
	}
}

