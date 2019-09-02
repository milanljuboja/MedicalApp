package com.stynt.services.officeServices;

import java.util.List;

import org.springframework.data.domain.Page;

import com.stynt.dto.OfficeDTO;
import com.stynt.entities.PagerModel;

public interface OfficeService {

	public Page<OfficeDTO> findAllPages(
			int pageSize, int page, int offset, 
			String column, String sortDirection, String type,
			String id, String name, String email, 
			String zipCode, String city, String state, 
			String phoneNumber, String emailActivationStatus,
			String status, String identityVerified, 
			String identityUnverified, String disabled, 
			String medical, String dental, String allAccountTypes, String allProfessions);
	
	public PagerModel createPagerModel(Page<OfficeDTO> offices, int buttons);
	
	public List<OfficeDTO> findAll(String column, String sortDirection, String type,
			String id, String name, String email, String zipCode, String city, String state, String phoneNumber,
			String emailActivationStatus, String status, String identityVerified, String identityUnverified,
			String disabled, String medical, String dental);
	
	public void updateIdentityVerification(boolean verification, int id);

}
