package com.car.rental.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.car.rental.entity.CarEntity;
import com.car.rental.entity.ReservationEntity;

public interface ReservationRepo extends JpaRepository<ReservationEntity, Long> {
	List<ReservationEntity> findByFromDateLessThanAndToDateGreaterThanAndCarEntity(Date toDate, Date fromDate,
			CarEntity carEntity);

}
