package com.maximyasn.restweathersensor.service;

import com.maximyasn.restweathersensor.domain.Sensor;
import com.maximyasn.restweathersensor.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SensorService implements ServiceInterface<Sensor> {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sensor> findAll() {
        return sensorRepository.findAll();
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<Sensor> findById(Integer id) {
        return sensorRepository.findById(id);
    }

    @Override
    @Transactional
    public void save(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    @Transactional(readOnly = true)
    public Optional<Sensor> findByName(String name) {
        return sensorRepository.findByName(name);
    }
}
