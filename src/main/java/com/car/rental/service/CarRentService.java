package com.car.rental.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.rental.dto.CarDto;
import com.car.rental.entity.CarEntity;
import com.car.rental.entity.ReservationEntity;
import com.car.rental.repo.CarRepo;
import com.car.rental.util.DateUtils;

@Service
public class CarRentService {
	@Autowired
	private CarRepo carRepo;

	@Autowired
	private ModelMapper mapper;

	@PostConstruct
	public void configure() {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}

	public List<CarDto> getCars(String fromDate, String toDate, String location, String category) {
		List<CarEntity> carEnties;
		if (fromDate != null && toDate != null && location != null && category != null) {
			carEnties = carRepo.findAll();
		} else if (fromDate != null && toDate != null && location != null) {
			carEnties = carRepo.findAll();
		} else if (fromDate != null && toDate != null && category != null) {
			carEnties = carRepo.findAll();
		} else if (location != null && category != null) {
			carEnties = carRepo.findByLocationIgnoreCaseAndCategoryIgnoreCase(location, category);
		} else if (location != null) {
			carEnties = carRepo.findByLocationIgnoreCase(location);
		} else if (category != null) {
			carEnties = carRepo.findByCategoryIgnoreCase(category);
		} else if (fromDate != null && toDate != null) {
			carEnties = carRepo.findAvailableCars(DateUtils.getDate(fromDate), DateUtils.getDate(toDate));
		} else {
			carEnties = carRepo.findAll();
		}
		List<CarDto> cars = mapToDto(carEnties);
		return cars;
	}

	private List<CarDto> mapToDto(List<CarEntity> carEnties) {
		List<CarDto> cars = new ArrayList<>();

		for (CarEntity carEntity : carEnties) {
			cars.add(mapper.map(carEntity, CarDto.class));
		}

		return cars;
	}

	public void createCar(CarDto car) {
		CarEntity carEntity = mapToCarEntity(car);
		carRepo.save(carEntity);
	}

	private CarEntity mapToCarEntity(CarDto car) {
		CarEntity carEntity = mapper.map(car, CarEntity.class);
		List<ReservationEntity> reservations = carEntity.getReservations();
		for (ReservationEntity reservation : reservations) {
			reservation.setCarEntity(carEntity);
		}
		return carEntity;
	}

	public CarDto getCarWithRegNo(String regNo) {
		CarEntity carEntity = carRepo.findByRegnoIgnoreCase(regNo);
		return mapper.map(carEntity, CarDto.class);
	}

	public List<CarDto> getCars(String fromDate, String toDate) {
		List<CarEntity> carEnties = carRepo.findAvailableCars(DateUtils.getDate(fromDate), DateUtils.getDate(toDate));
		List<CarDto> cars = mapToDto(carEnties);
		return cars;
	}

	public CarDto bookCar(CarDto car, String regNo) {
		CarEntity carEntityFromRepo = carRepo.findByRegnoIgnoreCase(regNo);
		CarEntity carEntity = mapToCarEntity(car);
		carEntity.setId(carEntityFromRepo.getId());
		carEntityFromRepo = carRepo.save(carEntity);
		return mapper.map(carEntityFromRepo, CarDto.class);
	}

}
