package com.maximyasn.restweathersensor.controller;


import com.maximyasn.restweathersensor.domain.Measurement;
import com.maximyasn.restweathersensor.dto.MeasurementDTO;
import com.maximyasn.restweathersensor.service.MeasurementService;
import com.maximyasn.restweathersensor.util.ErrorResponse;
import com.maximyasn.restweathersensor.util.ErrorToClient;
import com.maximyasn.restweathersensor.util.MeasurementValidator;
import com.maximyasn.restweathersensor.util.exception.SensorNotFoundException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {

    private final MeasurementService measurementService;

    private final ModelMapper modelMapper;

    private final MeasurementValidator measurementValidator;

    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper, MeasurementValidator measurementValidator) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;

    }

    @GetMapping
    public ResponseEntity<List<MeasurementDTO>> findAll() {
        List<MeasurementDTO> collect = measurementService.findAll()
                .stream()
                .map(measurement -> modelMapper.map(measurement, MeasurementDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(collect);
    }

    @GetMapping("/rainyDaysCount")
    public ResponseEntity<Map<String, Integer>> getRainyDaysCount() {
        Integer days = measurementService.countByRaining();
        String key = "Count of rainy days";
        Map<String, Integer> map = Collections.singletonMap(key, days);

        return ResponseEntity.ok(map);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addMeasurement(@RequestBody @Valid MeasurementDTO measurementDTO,
                                                     BindingResult bindingResult) {

        Measurement toAdd = modelMapper.map(measurementDTO, Measurement.class);

        measurementValidator.validate(toAdd, bindingResult);

        if(bindingResult.hasErrors()) {
            StringBuilder stringBuilder = ErrorToClient.returnErrorToClient(bindingResult);
            throw new SensorNotFoundException(stringBuilder.toString());
        }

        measurementService.save(toAdd);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<ErrorResponse> handleException(SensorNotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse(
                e.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

}
