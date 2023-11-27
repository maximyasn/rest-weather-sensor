package com.maximyasn.restweathersensor.service;

import com.maximyasn.restweathersensor.domain.Measurement;
import com.maximyasn.restweathersensor.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class MeasurementService implements ServiceInterface<Measurement> {

    private final MeasurementRepository measurementRepository;
    private final SensorService sensorService;

    @Autowired
    public MeasurementService(MeasurementRepository measurementRepository, SensorService sensorService) {
        this.measurementRepository = measurementRepository;
        this.sensorService = sensorService;
    }

    @Override
    public List<Measurement> findAll() {
        return measurementRepository.findAll();
    }

    @Override
    public Optional<Measurement> findById(Integer id) {
        return measurementRepository.findById(id);
    }

    @Override
    @Transactional
    public void save(Measurement measurement) {
        measurement.setSensor(sensorService.findByName(measurement.getSensor().getName()).get());
        measurementRepository.save(measurement);
    }

    public Integer countByRaining() {
        return measurementRepository.countAllByRainingIsTrue();
    }
}
