package com.stynt.export;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.stynt.dto.OfficeDTO;

public class ExportOfficesToCsv {

	public static void export(List<OfficeDTO> offices, HttpServletResponse response) throws IOException {
		String csvFileName = "offices.csv";
		response.setContentType("text/csv");
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=" + csvFileName);
		response.setHeader(headerKey, headerValue);

		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);

		String[] header = { "id", "phoneNumber", "email", "name", "stateAddress", "addressLine", "city",
				"numberOfClientOffices", "accountType", "status", "emailActivationStatus",
				"identityVerificationStatus", "registrationDate", "lastAccess", "lastWebAccess",
				"lastAndroidAccess", "lastIOSAccess" };

		for (OfficeDTO office : offices) {
			csvWriter.write(office, header);
		}
		csvWriter.close();
	}
}
