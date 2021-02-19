package com.estudos.springudemy.services.execptions;

public class DataIntegrityExecption extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public DataIntegrityExecption(String msg) {
        super(msg);
    }

    public DataIntegrityExecption(String msg, Throwable cause) {
        super(msg,cause);
    }
}
