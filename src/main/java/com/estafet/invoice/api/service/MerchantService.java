package com.estafet.invoice.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.estafet.invoice.api.exception.ResourceNotFoundException;
import com.estafet.invoice.api.model.Merchant;
import com.estafet.invoice.api.repository.MerchantRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MerchantService {

	private final MerchantRepository merchantRepository;

	public Merchant createMerchant(Merchant merchant) {
		return merchantRepository.save(merchant);
	}

	public Merchant getMerchant(Long id) {
		return merchantRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Merchant not found"));
	}

	public List<Merchant> getAllMerchants() {
		return merchantRepository.findAll();
	}

	public Merchant updateMerchant(Long id, Merchant merchantDetails) {
		Merchant merchant = getMerchant(id);
		merchant.setName(merchantDetails.getName());
		// Update other fields as needed...
		return merchantRepository.save(merchant);
	}

	public void deleteMerchant(Long id) {
		merchantRepository.deleteById(id);
	}
}
