package com.example.demo.exceptions;

import org.springframework.stereotype.Component;

@Component
public class CustomException extends RuntimeException {

	private String code;
	private String message;
	public CustomException()
	{
		
	}
	public CustomException(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "CustomException [code=" + code + ", message=" + message + "]";
	}
	
}

