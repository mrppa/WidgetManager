package com.mrppa.widgetmanager;

public class WidgetException extends Exception {
	private String exception = "";

	public WidgetException(String exception) {
		super();
		this.exception = exception;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

}
