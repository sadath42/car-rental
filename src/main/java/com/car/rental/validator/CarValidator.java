package com.car.rental.validator;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.car.rental.dto.CarDto;
import com.car.rental.dto.ErrorCode;
import com.car.rental.dto.ReservationDto;
import com.car.rental.entity.CarEntity;
import com.car.rental.entity.ReservationEntity;
import com.car.rental.exception.CarRentalValidationException;
import com.car.rental.repo.CarRepo;
import com.car.rental.repo.ReservationRepo;
import com.car.rental.util.DateUtils;

@Component
public class CarValidator {

	@Autowired
	private CarRepo carRepo;

	@Autowired
	private ReservationRepo reservationRepo;

	public void validateDates(String fromDate, String toDate) {

		if (!StringUtils.isEmpty(fromDate) && !StringUtils.isEmpty(toDate)) {
			validateFromAndToDate(fromDate, toDate);
		}
	}

	private void validateFromAndToDate(String fromDate, String toDate) {
		if (!DateUtils.isBeforeOrEquals(fromDate, toDate)) {
			throw new CarRentalValidationException("From date should be same or earlier than to date",
					ErrorCode.BAD_REQUEST);
		}
	}

	public void validateBooking(CarDto car, String regNo) {
		CarEntity carEntity = carRepo.findByRegnoIgnoreCase(regNo);
		if (carEntity == null) {
			throw new CarRentalValidationException("No car exists with the registration number : " + regNo,
					ErrorCode.BAD_REQUEST);
		}
		List<ReservationDto> reservations = car.getReservations();
		for (ReservationDto reservationDto : reservations) {
			Date fromDate = reservationDto.getFromDate();
			Date toDate = reservationDto.getToDate();
			if (!(fromDate.before(toDate) || fromDate.equals(toDate))) {
				throw new CarRentalValidationException("From date should be same or earlier than to date",
						ErrorCode.BAD_REQUEST);
			}

			List<ReservationEntity> reservationsEnties = reservationRepo
					.findByFromDateLessThanAndToDateGreaterThanAndCarEntity(toDate, fromDate, carEntity);

			if (reservationsEnties != null && !reservationsEnties.isEmpty()) {
				throw new CarRentalValidationException(
						String.format("Car is not avaiable for dates %1$s %2$s", reservationDto.getFromDate(), reservationDto.getToDate()),
						ErrorCode.BAD_REQUEST);
			}

		}
	}

}
