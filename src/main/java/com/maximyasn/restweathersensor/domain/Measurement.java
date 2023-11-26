package com.maximyasn.restweathersensor.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Entity
@Table(name = "measurement", schema = "sensor")
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private BigDecimal value;

    private Boolean raining;

    @ManyToOne
    @JoinColumn(name = "sensor_name", referencedColumnName = "name")
    private Sensor sensor;
}
