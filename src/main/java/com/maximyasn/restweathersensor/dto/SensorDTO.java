package com.maximyasn.restweathersensor.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SensorDTO {

    @NotEmpty(message = "Name must not be empty!")
    @Size(min = 2, max = 30, message = "Name must contain 2-30 symbols")
    private String name;

    @NotEmpty(message = "Location must not be empty!")
    @Size(min = 5, max = 100, message = "Location must contain 5-100 symbols")
    private String location;
}
