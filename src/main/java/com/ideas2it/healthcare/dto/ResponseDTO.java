package com.ideas2it.healthcare.dto;

import lombok.Data;

@Data
public class ResponseDTO {

	private String status;
	private Object data;
	private String message;
	private int errorCode;

	public void setSuccess(Object data, String message) {
		this.status = "success";
		this.data = data;
		this.message = message;
	}

	public void setError(int errorCode, String message) {
		this.status = "error";
		this.errorCode = errorCode;
		this.message = message;
	}

}
