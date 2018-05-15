package com.car.rental.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.car.rental.dto.CarDto;
import com.car.rental.service.CarRentService;
import com.car.rental.validator.CarValidator;

@RestController
@RequestMapping("/api")
public class CarRentController {

	@Autowired
	private CarRentService carRentService;
	
	@Autowired
	private CarValidator carValidator;


	@PostMapping(path = "/cars")
	public ResponseEntity<HttpStatus> createCar(@RequestBody CarDto car) {
		carRentService.createCar(car);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PutMapping(path = "/cars/{regno}")
	public ResponseEntity<CarDto> bookCar(@Valid @RequestBody CarDto car,@PathVariable(value = "regno", required = true) String regNo) {
		carValidator.validateBooking(car,regNo);
		CarDto carUpdate =carRentService.bookCar(car,regNo);
		return new ResponseEntity<>(carUpdate,HttpStatus.OK);
	}
 
	@GetMapping(path = "/cars/{regno}")
	public ResponseEntity<CarDto> getCarWithRegno(@PathVariable(value = "regno", required = true) String regNo) {
		return new ResponseEntity<>(carRentService.getCarWithRegNo(regNo), HttpStatus.OK);
	}

	@GetMapping(path = "/cars")
	public ResponseEntity<List<CarDto>> getCars(@RequestParam(value = "location", required = false) String location,
			@RequestParam(value = "category", required = false) String category,
			@RequestParam(value = "start", required = false) String fromDate,
			@RequestParam(value = "end", required = false) String toDate) {
		carValidator.validateDates(fromDate,toDate);
		return new ResponseEntity<>(carRentService.getCars(fromDate, toDate,location,category), HttpStatus.OK);
	}

}
