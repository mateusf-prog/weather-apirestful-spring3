package br.com.mateus.apiweather.exception;

public class ApiException extends RuntimeException {

    private String msg;

    public ApiException(String msg) {
        super(msg);
    }
}
