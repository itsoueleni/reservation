package com.example.demo.reservation.collection;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Accommodation {
    @ApiModelProperty(value = "The name or description of the accommodation")
    private String description;
    @ApiModelProperty(value = "The type of accommodation (e.g., 'Suite', 'Single Room')")
    private boolean available;
    @ApiModelProperty(value = "The price per night for the accommodation")
    public boolean isAvailable() {
        return available;
    }

}
