package com.car.rental.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.car.rental.entity.CarEntity;

public interface CarRepo extends JpaRepository<CarEntity, Long> {
	String AVAILABLE_CARS = "Select * From CAR c where not exists  (Select * From RESERVATION "
			+ "  Where car = c.id    And from_Date < :toDate     And to_Date > :fromDate)";

	String AVAILABLE_RESERVATION_FOR_CAR = "Select * From RESERVATION "
			+ "  Where car = :carid    And from_Date < :toDate     And to_Date > :fromDate";

	CarEntity findByRegnoIgnoreCase(String regno);

	List<CarEntity> findByLocationIgnoreCase(String location);

	List<CarEntity> findByCategoryIgnoreCase(String category);
	
	 

	@Query(value = AVAILABLE_CARS, nativeQuery = true)
	public List<CarEntity> findAvailableCars(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);

	@Query(value = AVAILABLE_RESERVATION_FOR_CAR, nativeQuery = true)
	public CarEntity isCarAvailable(@Param("carid") Long carid, @Param("fromDate") Date fromDate,
			@Param("toDate") Date toDate);

	List<CarEntity> findByLocationIgnoreCaseAndCategoryIgnoreCase(String location, String category);

}
