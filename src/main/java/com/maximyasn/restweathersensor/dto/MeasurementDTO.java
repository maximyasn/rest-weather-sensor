package com.maximyasn.restweathersensor.dto;

import com.maximyasn.restweathersensor.domain.Sensor;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class MeasurementDTO {

    @NotEmpty(message = "Value must not be empty!")
    @Size(min = -100, max = 100, message = "Value should be between -100 and 100 degrees!")
    private BigDecimal value;

    @NotEmpty(message = "Rain status should be declared!")
    private Boolean raining;

    @NotEmpty(message = "Source should be declared!")
    private Sensor sensor;
}
