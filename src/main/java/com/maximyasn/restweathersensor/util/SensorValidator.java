package com.maximyasn.restweathersensor.util;

import com.maximyasn.restweathersensor.domain.Sensor;
import com.maximyasn.restweathersensor.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorValidator(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;

        if(sensorRepository.findByName(sensor.getName()).isEmpty()) {
            errors.rejectValue("name", "Not Found",
                    "Sensor with this name doesn't exists!");
        }
    }
}
