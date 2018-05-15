package com.car.rental.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.car.rental.dto.ErrorCode;
import com.car.rental.exception.CarRentalValidationException;

public class DateUtils {

	static DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

	public static Date getDate(String fromDate) {
		try {
			return format.parse(fromDate);
		} catch (ParseException e) {
			throw new CarRentalValidationException("Invalid date format", e, ErrorCode.BAD_REQUEST);
		}
	}

	public static boolean isBeforeOrEquals(String fromDate, String toDate) {
		try {
			Date startDate = format.parse(fromDate);
			Date endDate = format.parse(toDate);
			return startDate.before(endDate) || startDate.equals(endDate);

		} catch (ParseException e) {
			throw new CarRentalValidationException("Invalid date format", e, ErrorCode.BAD_REQUEST);
		}
	}

}
