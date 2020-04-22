package com.meroproduction.rest.basic.auth;

public class BasicAuthenticationBean {

    private String message = null;

    public BasicAuthenticationBean(String message) {
	super();
	this.message = message;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    @Override
    public String toString() {
	return "BasicAuthenticationBean [message=" + message + "]";
    }

}
