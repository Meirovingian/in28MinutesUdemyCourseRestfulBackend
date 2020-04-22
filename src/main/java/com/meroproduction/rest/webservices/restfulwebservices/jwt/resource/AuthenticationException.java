package com.meroproduction.rest.webservices.restfulwebservices.jwt.resource;

public class AuthenticationException extends RuntimeException {

    private static final long serialVersionUID = 8826281649323659828L;

    public AuthenticationException(String message, Throwable cause) {
	super(message, cause);
    }
}
