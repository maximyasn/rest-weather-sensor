package com.maximyasn.restweathersensor.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "sensor", schema = "sensor")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String location;

    @OneToMany(mappedBy = "sensorName")
    private List<Measurement> measurements;
}
