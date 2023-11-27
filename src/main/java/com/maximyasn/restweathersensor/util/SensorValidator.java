package com.maximyasn.restweathersensor.util;

import com.maximyasn.restweathersensor.domain.Sensor;
import com.maximyasn.restweathersensor.dto.SensorDTO;
import com.maximyasn.restweathersensor.repository.SensorRepository;
import com.maximyasn.restweathersensor.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensor = (SensorDTO) target;

        if(sensorService.findByName(sensor.getName()).isPresent()) {
            errors.rejectValue("name",
                    "Sensor with this name already exists!");
        }
    }
}
