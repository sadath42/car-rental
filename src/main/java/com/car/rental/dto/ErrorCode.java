package com.car.rental.dto;

public enum ErrorCode {
	INVALID_FILE_FORMAT(601), INVALID_JSON(602), DOWNLOAD_FAILED(603), UPLOAD_FAILED(604), CONVERSION_ERROR(
			605), INTERNAL_ERROR(606), THUMBNAIL_CREATION_ERROR(607), PDF_IMAGE_ADD_ERROR(607), BAD_REQUEST(608);

	private Integer statusCode;

	private ErrorCode(Integer errCode) {
		statusCode = errCode;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	@Override
	public String toString() {
		return super.toString() + " : " + statusCode;
	}

}
