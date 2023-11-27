package com.maximyasn.restweathersensor.controller;

import com.maximyasn.restweathersensor.domain.Sensor;
import com.maximyasn.restweathersensor.dto.SensorDTO;
import com.maximyasn.restweathersensor.service.SensorService;
import com.maximyasn.restweathersensor.util.ErrorResponse;
import com.maximyasn.restweathersensor.util.ErrorToClient;
import com.maximyasn.restweathersensor.util.SensorValidator;
import com.maximyasn.restweathersensor.util.exception.SensorNotCreatedException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;

    private final SensorValidator sensorValidator;
    private final ModelMapper modelMapper;

    @Autowired
    public SensorController(SensorService service, SensorValidator sensorValidator, ModelMapper modelMapper) {
        this.sensorService = service;
        this.sensorValidator = sensorValidator;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> register(@RequestBody @Valid SensorDTO sensorDTO,
                                               BindingResult bindingResult) {
        sensorValidator.validate(sensorDTO, bindingResult);

        if(bindingResult.hasErrors()) {
            StringBuilder stringBuilder = ErrorToClient.returnErrorToClient(bindingResult);
            throw new SensorNotCreatedException(stringBuilder.toString());
        }

        sensorService.save(modelMapper.map(sensorDTO, Sensor.class));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(SensorNotCreatedException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
