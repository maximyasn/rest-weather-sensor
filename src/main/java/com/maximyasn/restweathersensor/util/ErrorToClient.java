package com.maximyasn.restweathersensor.util;

import com.maximyasn.restweathersensor.util.exception.SensorNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorToClient {

    public static StringBuilder returnErrorToClient(BindingResult bindingResult) {
        StringBuilder sb = new StringBuilder();
        List<FieldError> errors = bindingResult.getFieldErrors();
        for (FieldError error : errors) {
            sb.append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage() == null ? error.getCode() : error.getDefaultMessage())
                    .append("; ");
        }

        return sb;
    }
}
