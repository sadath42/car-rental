package com.car.rental.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "RESERVATION")
public class ReservationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Date fromDate;

	private Date toDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CAR", nullable = false)
	private CarEntity carEntity;

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

	public CarEntity getCarEntity() {
		return carEntity;
	}

	public void setCarEntity(CarEntity carEntity) {
		this.carEntity = carEntity;
	}
}
