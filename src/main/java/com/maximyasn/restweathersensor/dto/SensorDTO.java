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

}
