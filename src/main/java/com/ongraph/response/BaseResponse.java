package com.ongraph.response;

import org.springframework.http.HttpStatus;

public class BaseResponse {
	private boolean success = false;
	private HttpStatus status;
	private String message;

	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}
