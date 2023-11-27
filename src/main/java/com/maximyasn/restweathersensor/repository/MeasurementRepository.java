package com.maximyasn.restweathersensor.repository;

import com.maximyasn.restweathersensor.domain.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {

    Integer countAllByRainingIsTrue();
}
