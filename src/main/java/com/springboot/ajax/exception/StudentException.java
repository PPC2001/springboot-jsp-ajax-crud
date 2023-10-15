package com.springboot.ajax.exception;

public class StudentException extends Exception {

	private static final long serialVersionUID = 1L;

	public StudentException(String errorCode) {
		super(errorCode);
	}

}
