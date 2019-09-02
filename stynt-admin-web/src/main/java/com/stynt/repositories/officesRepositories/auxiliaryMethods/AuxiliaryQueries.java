package com.stynt.repositories.officesRepositories.auxiliaryMethods;

import java.util.List;

import com.stynt.repositories.officesRepositories.auxiliaryClasses.CountOffices;

public class AuxiliaryQueries {

	public String queryCompletion(String sql, int pageSize, int offset, String column, String sortDirection,
			String id, String name, String email, String zipCode, String city, String state, String phoneNumber,
			String emailActivationStatus, String status, String identityVerified, String identityUnverified,
			String disabled, String medical, String dental) {

		if (id != "") {
			sql = sql + "AND u.id = :id ";
		}
		if (name != "") {
			sql = sql + "AND u.practiceName LIKE :name ";
		}
		if (email != "") {
			sql = sql + "AND u.email LIKE :email ";
		}
		if (zipCode != "") {
			sql = sql + "AND adr.zipCode = :zipCode ";
		}
		if (city != "") {
			sql = sql + "AND adr.city LIKE :city ";
		}
		if (state != "") {
			sql = sql + "AND adr.state LIKE :state ";
		}
		if (phoneNumber != "") {
			sql = sql + "AND cn.number LIKE :phoneNumber ";
		}
		if (emailActivationStatus != "") {
			sql = sql + "AND u.activationStatus = :emailActivationStatus ";
		}
		if (status != "") {
			sql = sql + "AND u.status = :status ";
		}
		if (identityVerified != "") {
			sql = sql + "AND u.serviceObjectsVerified = :identityVerified ";
		}
		if (identityUnverified != "") {
			sql = sql + "AND u.serviceObjectsVerified = :identityUnverified ";
		}
		if (disabled != "") {
			sql = sql + "AND u.banStatus = :disabled ";
		}
		if (medical != "") {
			sql = sql + "AND uspr.profession_id = :medical ";
		}
		if (dental != "") {
			sql = sql + "AND uspr.profession_id = :dental ";
		}
		
		sql = sql + "ORDER BY u." + column + " " + sortDirection + " LIMIT " + pageSize + " OFFSET " + offset;
		
		return sql;
	}

	public CountOffices countQueryCompletion(String sql, List<Object> obj, String id, String name,
			String email, String zipCode, String city, String state, String phoneNumber, String emailActivationStatus,
			String status, String identityVerified, String identityUnverified, String disabled, String medical,
			String dental) {

		if (id != "") {
			sql = sql + "AND u.id = ? ";
			obj.add(id);
		}
		if (name != "") {
			sql = sql + "AND u.practiceName LIKE ? ";
			obj.add("%" + name + "%");
		}
		if (email != "") {
			sql = sql + "AND u.email LIKE ? ";
			obj.add("%" + email + "%");
		}
		if (zipCode != "") {
			sql = sql + "AND adr.zipCode = ? ";
			obj.add(zipCode);
		}
		if (city != "") {
			sql = sql + "AND adr.city LIKE ? ";
			obj.add("%" + city + "%");
		}
		if (state != "") {
			sql = sql + "AND adr.state LIKE ? ";
			obj.add("%" + state + "%");
		}
		if (phoneNumber != "") {
			sql = sql + "AND cn.number LIKE ? ";
			obj.add("%" + phoneNumber + "%");
		}
		if (emailActivationStatus != "") {
			sql = sql + "AND u.activationStatus = ? ";
			obj.add(emailActivationStatus);
		}
		if (status != "") {
			sql = sql + "AND u.status = ? ";
			obj.add(status);
		}
		if (identityVerified != "") {
			sql = sql + "AND u.serviceObjectsVerified = ? ";
			obj.add(identityVerified);
		}
		if (identityUnverified != "") {
			sql = sql + "AND u.serviceObjectsVerified = ? ";
			obj.add(identityUnverified);
		}
		if (disabled != "") {
			sql = sql + "AND u.banStatus = ? ";
			obj.add(disabled);
		}
		if (medical != "") {
			sql = sql + "AND uspr.profession_id = ? ";
			obj.add(medical);
		}
		if (dental != "") {
			sql = sql + "AND uspr.profession_id = ? ";
			obj.add(dental);
		}

		return new CountOffices(sql, obj);
	}
	
	public String queryCompletionForCsv(String sql, String column, String sortDirection,
			String id, String name, String email, String zipCode, String city, String state, String phoneNumber,
			String emailActivationStatus, String status, String identityVerified, String identityUnverified,
			String disabled, String medical, String dental) {

		if (id != "") {
			sql = sql + "AND u.id = :id ";
		}
		if (name != "") {
			sql = sql + "AND u.practiceName LIKE :name ";
		}
		if (email != "") {
			sql = sql + "AND u.email LIKE :email ";
		}
		if (zipCode != "") {
			sql = sql + "AND adr.zipCode = :zipCode ";
		}
		if (city != "") {
			sql = sql + "AND adr.city LIKE :city ";
		}
		if (state != "") {
			sql = sql + "AND adr.state LIKE :state ";
		}
		if (phoneNumber != "") {
			sql = sql + "AND cn.number LIKE :phoneNumber ";
		}
		if (emailActivationStatus != "") {
			sql = sql + "AND u.activationStatus = :emailActivationStatus ";
		}
		if (status != "") {
			sql = sql + "AND u.status = :status ";
		}
		if (identityVerified != "") {
			sql = sql + "AND u.serviceObjectsVerified = :identityVerified ";
		}
		if (identityUnverified != "") {
			sql = sql + "AND u.serviceObjectsVerified = :identityUnverified ";
		}
		if (disabled != "") {
			sql = sql + "AND u.banStatus = :disabled ";
		}
		if (medical != "") {
			sql = sql + "AND uspr.profession_id = :medical ";
		}
		if (dental != "") {
			sql = sql + "AND uspr.profession_id = :dental ";
		}
		
		sql = sql + "ORDER BY u." + column + " " + sortDirection;
		
		return sql;
	}
}
