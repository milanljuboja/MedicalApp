package com.stynt.repositories.officesRepositories.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.stynt.dto.OfficeDTO;
import com.stynt.dto.mappers.OfficeRowMapper;
import com.stynt.repositories.officesRepositories.OfficeRepository;
import com.stynt.repositories.officesRepositories.auxiliaryClasses.CountOffices;
import com.stynt.repositories.officesRepositories.auxiliaryMethods.AuxiliaryQueries;

@Repository
public class OfficeRepositoryImpl extends AuxiliaryQueries implements OfficeRepository {

	@Qualifier("namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate template;
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public OfficeRepositoryImpl(NamedParameterJdbcTemplate template, JdbcTemplate jdbcTemplate) {
		this.template = template;
		this.jdbcTemplate = jdbcTemplate;
	}

	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
		return template;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public List<OfficeDTO> findOfficesWithLimitOffset(int pageSize, int offset, String column, String sortDirection,
			String type, String id, String name, String email, String zipCode, String city, String state,
			String phoneNumber, String emailActivationStatus, String status, String identityVerified,
			String identityUnverified, String disabled, String medical, String dental) {

		String sql = "SELECT u.id AS id, " + "cn.number as phoneNumber, " + "u.email AS email, "
				+ "u.practiceName AS name, " + "adr.state AS stateAddress, " + "adr.addressLine AS addressLine, "
				+ "adr.city AS city, " + "(SELECT COUNT(0) " + "FROM ClientOffice co "
				+ "WHERE (co.user_id = u.id)) AS numberOfClientOffices, " + "u.userSource as accountType, "
				+ "u.status AS status, " + "u.activationStatus AS emailActivationStatus, "
				+ "u.serviceObjectsVerified AS identityVerificationStatus, " + "u.dateRegistered AS registrationDate, "
				+ "u.lastAccess as lastAccess, " + "u.lastAccessWeb lastWebAccess, "
				+ "u.lastAccessAndroid as lastAndroidAccess, " + "u.lastAccessiOS as lastIOSAccess " + "FROM (((User u "
				+ "LEFT JOIN ContactNumber cn ON (u.id = cn.user_id)) "
				+ "LEFT JOIN Address adr ON (u.primaryAddress_id = adr.id)) "
				+ "LEFT JOIN Userprofile uspr ON (u.userProfile_id = uspr.id)) " + "WHERE u.type = :type ";

		String query = queryCompletion(sql, pageSize, offset, column, sortDirection, id, name, email, zipCode, city,
				state, phoneNumber, emailActivationStatus, status, identityVerified, identityUnverified, disabled,
				medical, dental);

		List<OfficeDTO> offices = getNamedParameterJdbcTemplate().query(query, new MapSqlParameterSource()
				.addValue("type", type).addValue("id", id).addValue("name", "%" + name + "%")
				.addValue("email", "%" + email + "%").addValue("zipCode", zipCode).addValue("city", "%" + city + "%")
				.addValue("state", "%" + state + "%").addValue("phoneNumber", "%" + phoneNumber + "%")
				.addValue("emailActivationStatus", emailActivationStatus).addValue("status", status)
				.addValue("identityVerified", identityVerified).addValue("identityUnverified", identityUnverified)
				.addValue("disabled", disabled).addValue("medical", medical).addValue("dental", dental),
				new OfficeRowMapper());

		return offices;
	}

	@Override
	public Long count(String type, String id, String name, String email, String zipCode, String city, String state,
			String phoneNumber, String emailActivationStatus, String status, String identityVerified,
			String identityUnverified, String disabled, String medical, String dental) {

		String sql = "SELECT count(0) FROM (((User u LEFT JOIN ContactNumber cn ON (u.id = cn.user_id)) "
				+ "LEFT JOIN Address adr ON (u.primaryAddress_id = adr.id)) "
				+ "LEFT JOIN Userprofile uspr ON (u.userProfile_id = uspr.id)) WHERE u.type = ? ";

		List<Object> newObj = new ArrayList<>();
		newObj.add(type);

		CountOffices countClass = countQueryCompletion(sql, newObj, id, name, email, zipCode, city, state, phoneNumber,
				emailActivationStatus, status, identityVerified, identityUnverified, disabled, medical, dental);

		return getJdbcTemplate().queryForObject(countClass.sql, countClass.obj.toArray(), Long.class);
	}

	@Override
	public List<OfficeDTO> findAll(String column, String sortDirection, String type, String id, String name,
			String email, String zipCode, String city, String state, String phoneNumber, String emailActivationStatus,
			String status, String identityVerified, String identityUnverified, String disabled, String medical,
			String dental) {

		String sql = "SELECT u.id AS id, " + "cn.number as phoneNumber, " + "u.email AS email, "
				+ "u.practiceName AS name, " + "adr.state AS stateAddress, " + "adr.addressLine AS addressLine, "
				+ "adr.city AS city, " + "(SELECT COUNT(0) " + "FROM ClientOffice co "
				+ "WHERE (co.user_id = u.id)) AS numberOfClientOffices, " + "u.userSource as accountType, "
				+ "u.status AS status, " + "u.activationStatus AS emailActivationStatus, "
				+ "u.serviceObjectsVerified AS identityVerificationStatus, " + "u.dateRegistered AS registrationDate, "
				+ "u.lastAccess as lastAccess, " + "u.lastAccessWeb lastWebAccess, "
				+ "u.lastAccessAndroid as lastAndroidAccess, " + "u.lastAccessiOS as lastIOSAccess " + "FROM (((User u "
				+ "LEFT JOIN ContactNumber cn ON (u.id = cn.user_id)) "
				+ "LEFT JOIN Address adr ON (u.primaryAddress_id = adr.id)) "
				+ "LEFT JOIN Userprofile uspr ON (u.userProfile_id = uspr.id)) " + "WHERE u.type = :type ";

		String query = queryCompletionForCsv(sql, column, sortDirection, id, name, email, zipCode, city, state,
				phoneNumber, emailActivationStatus, status, identityVerified, identityUnverified, disabled, medical,
				dental);

		List<OfficeDTO> offices = getNamedParameterJdbcTemplate().query(query, new MapSqlParameterSource()
				.addValue("type", type).addValue("id", id).addValue("name", "%" + name + "%")
				.addValue("email", "%" + email + "%").addValue("zipCode", zipCode).addValue("city", "%" + city + "%")
				.addValue("state", "%" + state + "%").addValue("phoneNumber", "%" + phoneNumber + "%")
				.addValue("emailActivationStatus", emailActivationStatus).addValue("status", status)
				.addValue("identityVerified", identityVerified).addValue("identityUnverified", identityUnverified)
				.addValue("disabled", disabled).addValue("medical", medical).addValue("dental", dental),
				new OfficeRowMapper());

		return offices;
	}

	@Override
	public void updateIdentityVerification(boolean verification, int id) {
		String updateQuery = "UPDATE user SET serviceObjectsVerified = ? where id = ? ";
		jdbcTemplate.update(updateQuery, new Object[] {verification, id});
	}

}
