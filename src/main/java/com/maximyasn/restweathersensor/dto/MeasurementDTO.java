package com.maximyasn.restweathersensor.dto;

import com.maximyasn.restweathersensor.domain.Sensor;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MeasurementDTO {

    @DecimalMin(value = "-100.0", message = "Temperature must be higher than -100")
    @DecimalMax(value = "100.0", message = "Temperature must be lower than 100")
    @Digits(integer = 3, fraction = 2, message = "Temperature can have 3 digs on left side and 2 on right side")
    private BigDecimal value;

    @NotNull(message = "Rain status should be declared!")
    private Boolean raining;

    @NotNull(message = "Source should be declared!")
    private SensorDTO sensor;
}
