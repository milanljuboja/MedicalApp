package com.stynt.dto;

import java.sql.Timestamp;

public class OfficeDTO {

	private Long id;
	private String phoneNumber;
	private String email;
	private String name;
	private String stateAddress;
	private String addressLine;
	private String city;
	private int numberOfClientOffices;
	private String accountType;
	private String status;
	private String emailActivationStatus;
	private Boolean identityVerificationStatus;
	private String registrationDate;
	private Timestamp lastAccess;
	private Timestamp lastWebAccess;
	private Timestamp lastAndroidAccess;
	private Timestamp lastIOSAccess;

	public OfficeDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStateAddress() {
		return stateAddress;
	}

	public void setStateAddress(String stateAddress) {
		this.stateAddress = stateAddress;
	}

	public String getAddressLine() {
		return addressLine;
	}

	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getNumberOfClientOffices() {
		return numberOfClientOffices;
	}

	public void setNumberOfClientOffices(int numberOfClientOffices) {
		this.numberOfClientOffices = numberOfClientOffices;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmailActivationStatus() {
		return emailActivationStatus;
	}

	public void setEmailActivationStatus(String emailActivationStatus) {
		this.emailActivationStatus = emailActivationStatus;
	}

	public Boolean getIdentityVerificationStatus() {
		return identityVerificationStatus;
	}

	public void setIdentityVerificationStatus(Boolean identityVerificationStatus) {
		this.identityVerificationStatus = identityVerificationStatus;
	}

	public String getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(String registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Timestamp getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(Timestamp lastAccess) {
		this.lastAccess = lastAccess;
	}

	public Timestamp getLastWebAccess() {
		return lastWebAccess;
	}

	public void setLastWebAccess(Timestamp lastWebAccess) {
		this.lastWebAccess = lastWebAccess;
	}

	public Timestamp getLastAndroidAccess() {
		return lastAndroidAccess;
	}

	public void setLastAndroidAccess(Timestamp lastAndroidAccess) {
		this.lastAndroidAccess = lastAndroidAccess;
	}

	public Timestamp getLastIOSAccess() {
		return lastIOSAccess;
	}

	public void setLastIOSAccess(Timestamp lastIOSAccess) {
		this.lastIOSAccess = lastIOSAccess;
	}

}
