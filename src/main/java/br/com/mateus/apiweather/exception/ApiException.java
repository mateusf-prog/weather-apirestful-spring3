package br.com.mateus.apiweather.exception;

public class ApiException extends RuntimeException {

    public ApiException(String msg) {
        super(msg);
    }
}
