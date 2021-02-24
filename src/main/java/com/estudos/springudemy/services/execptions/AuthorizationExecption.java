package com.estudos.springudemy.services.execptions;

public class AuthorizationExecption extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public AuthorizationExecption(String msg) {
        super(msg);
    }

    public AuthorizationExecption(String msg, Throwable cause) {
        super(msg,cause);
    }
}
