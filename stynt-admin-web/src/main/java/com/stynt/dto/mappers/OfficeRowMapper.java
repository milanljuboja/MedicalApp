package com.stynt.dto.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.stynt.dto.OfficeDTO;

public class OfficeRowMapper implements RowMapper<OfficeDTO>{

	@Override
	public OfficeDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
		OfficeDTO office = new OfficeDTO();
		
		office.setId(rs.getLong("id"));
		office.setPhoneNumber(rs.getString("phoneNumber"));
		office.setEmail(rs.getString("email"));
		office.setName(rs.getString("name"));
		office.setStateAddress(rs.getString("stateAddress"));
		office.setAddressLine(rs.getString("addressLine"));
		office.setCity(rs.getString("city"));
		office.setNumberOfClientOffices(rs.getFetchSize());
		office.setAccountType(rs.getString("accountType"));
		office.setStatus(rs.getString("status"));
		office.setEmailActivationStatus(rs.getString("emailActivationStatus"));
		office.setIdentityVerificationStatus(rs.getBoolean("identityVerificationStatus"));
		office.setRegistrationDate(rs.getString("registrationDate"));
		office.setLastAccess(rs.getTimestamp("lastAccess"));
		office.setLastWebAccess(rs.getTimestamp("lastWebAccess"));
		office.setLastAndroidAccess(rs.getTimestamp("lastAndroidAccess"));
		office.setLastIOSAccess(rs.getTimestamp("lastIOSAccess"));
		
		return office;
	}

}
