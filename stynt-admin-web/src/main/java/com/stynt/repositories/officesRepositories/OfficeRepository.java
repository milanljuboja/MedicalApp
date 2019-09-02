package com.stynt.repositories.officesRepositories;

import java.util.List;

import com.stynt.dto.OfficeDTO;

public interface OfficeRepository {

	public List<OfficeDTO> findOfficesWithLimitOffset(
			int pageSize, int offset, 
			String column, String sortDirection, String type, 
			String id, String name, String email, 
			String zipCode, String city, String state, 
			String phoneNumber, String emailActivationStatus, 
			String status, String identityVerified, 
			String identityUnverified, String disabled, 
			String medical, String dental);
	
	public Long count(
			String type, String id, String name, String email, 
			String zipCode, String city, String state, String phoneNumber, 
			String emailActivationStatus, String status, String identityVerified, 
			String identityUnverified, String disabled, String medical, String dental);
	
	public List<OfficeDTO> findAll(String column, String sortDirection, String type,
			String id, String name, String email, String zipCode, String city, String state, String phoneNumber,
			String emailActivationStatus, String status, String identityVerified, String identityUnverified,
			String disabled, String medical, String dental);
	
	public void updateIdentityVerification(boolean verification, int id);
}
