package com.car.rental.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReservationDto {

	@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	private Date fromDate;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@NotNull
	private Date toDate;

	private String companyTag;

	private String customerMail;

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getCompanyTag() {
		return companyTag;
	}

	public void setCompanyTag(String companyTag) {
		this.companyTag = companyTag;
	}

	public String getCustomerMail() {
		return customerMail;
	}

	public void setCustomerMail(String customerMail) {
		this.customerMail = customerMail;
	}

	@Override
	public String toString() {
		return "ClassPojo [fromDate = " + fromDate + ", toDate = " + toDate + ", companyTag = " + companyTag
				+ ", customerMail = " + customerMail + "]";
	}
}
