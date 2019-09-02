package com.stynt.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.stynt.dto.OfficeDTO;
import com.stynt.export.ExportOfficesToCsv;
import com.stynt.services.officeServices.OfficeService;

/**
 * FIXME: 
 * @author Milan Ljuboja
 */
@Controller
@RequestMapping("")
public class UserController {

	private static final int INITIAL_PAGE = 0;
	private static final int INITIAL_PAGE_SIZE = 5;
	private static final int[] PAGE_SIZES = { 1, 5, 10 };
	private static final String USER_TYPE_OFFICE = "CLIENT";
	private static final int BUTTONS_TO_SHOW = 3;

	private OfficeService officeService;

	@Autowired
	public UserController(OfficeService officeService) {
		super();
		this.officeService = officeService;
	}

	@GetMapping("")
	public String index() {
		return "dashboard";
	}

	@GetMapping("/officeAdministration")
	public ModelAndView administrationOfOffices(@RequestParam("pageSize") Optional<Integer> pageSize,
			@RequestParam("page") Optional<Integer> page, @RequestParam("sortDirection") Optional<String> sortDirection,
			@RequestParam("sortBy") Optional<String> sortBy, @RequestParam("id") Optional<String> id,
			@RequestParam("name") Optional<String> name, @RequestParam("email") Optional<String> email,
			@RequestParam("zipCode") Optional<String> zipCode, @RequestParam("city") Optional<String> city,
			@RequestParam("state") Optional<String> state, @RequestParam("phoneNumber") Optional<String> phoneNumber,
			@RequestParam("emailActivationStatus") Optional<String> emailActivationStatus,
			@RequestParam("status") Optional<String> status,
			@RequestParam("identityVerified") Optional<String> identityVerified,
			@RequestParam("identityUnverified") Optional<String> identityUnverified,
			@RequestParam("disabled") Optional<String> disabled, @RequestParam("medical") Optional<String> medical,
			@RequestParam("dental") Optional<String> dental,
			@RequestParam("allAccountTypes") Optional<String> allAccountTypes,
			@RequestParam("allProfessions") Optional<String> allProfessions,
			@RequestParam("exportToCsv") Optional<String> exportToCsv,
			@RequestParam("verifyIdentity") Optional<Integer> verifyIdentity,
			HttpServletResponse response)
			throws IOException {

		ModelAndView modelAndView = new ModelAndView("administrationOfOffices");

		if (exportToCsv.orElse("") != "") {
			List<OfficeDTO> offices = officeService.findAll(sortBy.orElse("id"), sortDirection.orElse("ASC"),
					USER_TYPE_OFFICE, id.orElse(""), name.orElse(""), email.orElse(""), zipCode.orElse(""),
					city.orElse(""), state.orElse(""), phoneNumber.orElse(""), emailActivationStatus.orElse(""),
					status.orElse(""), identityVerified.orElse(""), identityUnverified.orElse(""), disabled.orElse(""),
					medical.orElse(""), dental.orElse(""));
			ExportOfficesToCsv.export(offices, response);
		}
		
		if(verifyIdentity.orElse(0) != 0) {
			officeService.updateIdentityVerification(true, verifyIdentity.orElse(0));
			
		}

		Page<OfficeDTO> offices = officeService.findAllPages(pageSize.orElse(INITIAL_PAGE_SIZE),
				(page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1,
				((page.orElse(0) < 1) ? INITIAL_PAGE : page.get() - 1) * pageSize.orElse(INITIAL_PAGE_SIZE),
				sortBy.orElse("id"), sortDirection.orElse("ASC"), USER_TYPE_OFFICE, id.orElse(""), name.orElse(""),
				email.orElse(""), zipCode.orElse(""), city.orElse(""), state.orElse(""), phoneNumber.orElse(""),
				emailActivationStatus.orElse(""), status.orElse(""), identityVerified.orElse(""),
				identityUnverified.orElse(""), disabled.orElse(""), medical.orElse(""), dental.orElse(""),
				allAccountTypes.orElse(""), allProfessions.orElse(""));

		modelAndView.addObject("offices", offices);
		modelAndView.addObject("selectedPageSize", pageSize.orElse(INITIAL_PAGE_SIZE));
		modelAndView.addObject("pageSizes", PAGE_SIZES);
		modelAndView.addObject("pager", officeService.createPagerModel(offices, BUTTONS_TO_SHOW));
		modelAndView.addObject("sortDirection",
				sortDirection.isPresent() ? sortDirection.get() : Sort.Direction.ASC.name());
		modelAndView.addObject("sortBy", sortBy.orElse("id"));
		modelAndView.addObject("pageNumber", page.orElse(1));
		modelAndView.addObject("id", id.orElse(""));
		modelAndView.addObject("name", name.orElse(""));
		modelAndView.addObject("email", email.orElse(""));
		modelAndView.addObject("zipCode", zipCode.orElse(""));
		modelAndView.addObject("city", city.orElse(""));
		modelAndView.addObject("state", state.orElse(""));
		modelAndView.addObject("phoneNumber", phoneNumber.orElse(""));
		modelAndView.addObject("emailActivationStatus", emailActivationStatus.orElse(""));
		modelAndView.addObject("status", status.orElse(""));
		modelAndView.addObject("identityVerified", identityVerified.orElse(""));
		modelAndView.addObject("identityUnverified", identityUnverified.orElse(""));
		modelAndView.addObject("disabled", disabled.orElse(""));
		modelAndView.addObject("medical", medical.orElse(""));
		modelAndView.addObject("dental", dental.orElse(""));
		modelAndView.addObject("allAccountTypes", allAccountTypes.orElse(""));
		modelAndView.addObject("allProfessions", allProfessions.orElse(""));
		
		return modelAndView;
	}
	
}
