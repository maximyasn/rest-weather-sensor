package com.maximyasn.restweathersensor.util.exception;

public class SensorNotFoundException extends RuntimeException {
    public SensorNotFoundException(String message) {
        super(message);
    }
}
