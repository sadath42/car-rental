package com.car.rental.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.car.rental.dto.ReservationDto;

@Entity
@Table(name = "CAR")
public class CarEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Integer doors;

	private String picture;

	private String logo;

	private String model;

	private String category;

	private Boolean aircondition;

	private String gear;

	private String location;

	@Column(unique=true)
	private String regno;

	private String company;

	private Integer priceperday;

	private Integer seats;

	private Integer year;

	private String make;

	@OneToMany(cascade = { CascadeType.ALL, CascadeType.PERSIST,
			CascadeType.MERGE }, fetch = FetchType.LAZY, mappedBy = "carEntity")
	private List<ReservationEntity> reservations;

	public Integer getDoors() {
		return doors;
	}

	public void setDoors(Integer doors) {
		this.doors = doors;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Boolean getAircondition() {
		return aircondition;
	}

	public void setAircondition(Boolean aircondition) {
		this.aircondition = aircondition;
	}

	public String getGear() {
		return gear;
	}

	public void setGear(String gear) {
		this.gear = gear;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRegno() {
		return regno;
	}

	public void setRegno(String regno) {
		this.regno = regno;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public Integer getPriceperday() {
		return priceperday;
	}

	public void setPriceperday(Integer priceperday) {
		this.priceperday = priceperday;
	}

	public Integer getSeats() {
		return seats;
	}

	public void setSeats(Integer seats) {
		this.seats = seats;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public List<ReservationEntity> getReservations() {
		return reservations;
	}

	public void setReservations(List<ReservationEntity> reservations) {
		this.reservations = reservations;
	}

	@Override
	public String toString() {
		return "ClassPojo [doors = " + doors + ", picture = " + picture + ", logo = " + logo + ", model = " + model
				+ ", category = " + category + ", aircondition = " + aircondition + ", gear = " + gear + ", location = "
				+ location + ", regno = " + regno + ", company = " + company + ", priceperday = " + priceperday
				+ ", seats = " + seats + ", year = " + year + ", make = " + make + ", reservations = " + reservations
				+ "]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
