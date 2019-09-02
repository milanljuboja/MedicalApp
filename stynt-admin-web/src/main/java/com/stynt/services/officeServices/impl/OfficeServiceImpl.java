package com.stynt.services.officeServices.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.stynt.dto.OfficeDTO;
import com.stynt.entities.PagerModel;
import com.stynt.repositories.officesRepositories.OfficeRepository;
import com.stynt.services.officeServices.OfficeService;

@Service
public class OfficeServiceImpl implements OfficeService {

	private OfficeRepository officeRepository;

	@Autowired
	public OfficeServiceImpl(OfficeRepository officeRepository) {
		this.officeRepository = officeRepository;
	}

	@Override
	public Page<OfficeDTO> findAllPages(int pageSize, int page, int offset, String column, String sortDirection,
			String type, String id, String name, String email, String zipCode, String city, String state,
			String phoneNumber, String emailActivationStatus, String status, String identityVerified,
			String identityUnverified, String disabled, String medical, String dental, String allAccountTypes,
			String allProfessions) {

		Sort sort = new Sort(Direction.fromString(sortDirection), column);
		PageRequest pageable = PageRequest.of(page, pageSize, sort);

		List<OfficeDTO> list;

		if (allAccountTypes != "" && allProfessions != "") {
			list = officeRepository.findOfficesWithLimitOffset(pageSize, offset, column, sortDirection, type, id, name,
					email, zipCode, city, state, phoneNumber, "", "", "", "", "", "", "");
			return new PageImpl<>(list, pageable, officeRepository.count(type, id, name, email, zipCode, city, state,
					phoneNumber, "", "", "", "", "", "", ""));
		} else if (allAccountTypes != "" && allProfessions == "") {
			list = officeRepository.findOfficesWithLimitOffset(pageSize, offset, column, sortDirection, type, id, name,
					email, zipCode, city, state, phoneNumber, "", "", "", "", "", medical, dental);
			return new PageImpl<>(list, pageable, officeRepository.count(type, id, name, email, zipCode, city, state,
					phoneNumber, "", "", "", "", "", medical, dental));
		} else if (allAccountTypes == "" && allProfessions != "") {
			list = officeRepository.findOfficesWithLimitOffset(pageSize, offset, column, sortDirection, type, id, name,
					email, zipCode, city, state, phoneNumber, emailActivationStatus, status, identityVerified,
					identityUnverified, disabled, "", "");
			return new PageImpl<>(list, pageable,
					officeRepository.count(type, id, name, email, zipCode, city, state, phoneNumber,
							emailActivationStatus, status, identityVerified, identityUnverified, disabled, "", ""));
		} else {
			list = officeRepository.findOfficesWithLimitOffset(pageSize, offset, column, sortDirection, type, id, name,
					email, zipCode, city, state, phoneNumber, emailActivationStatus, status, identityVerified,
					identityUnverified, disabled, medical, dental);
			return new PageImpl<>(list, pageable,
					officeRepository.count(type, id, name, email, zipCode, city, state, phoneNumber,
							emailActivationStatus, status, identityVerified, identityUnverified, disabled, medical,
							dental));
		}
	}

	@Override
	public PagerModel createPagerModel(Page<OfficeDTO> offices, int buttons) {

		return new PagerModel(offices.getTotalPages(), offices.getNumber(), buttons);
	}

	@Override
	public List<OfficeDTO> findAll(String column, String sortDirection, String type, String id, String name,
			String email, String zipCode, String city, String state, String phoneNumber, String emailActivationStatus,
			String status, String identityVerified, String identityUnverified, String disabled, String medical,
			String dental) {
		return officeRepository.findAll(column, sortDirection, type, id, name, email, zipCode, city, state, phoneNumber,
				emailActivationStatus, status, identityVerified, identityUnverified, disabled, medical, dental);
	}

	@Override
	public void updateIdentityVerification(boolean verification, int id) {
		officeRepository.updateIdentityVerification(verification, id);
	}

}
